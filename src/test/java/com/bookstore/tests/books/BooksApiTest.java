package com.bookstore.tests.books;
 
import com.bookstore.clients.BookApiClient;
import com.bookstore.testdata.TestData;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
 
import java.util.List;
import java.util.Map;
import java.util.Set;
 
import static org.junit.jupiter.api.Assertions.*;
 
class BooksApiTest {
    private final BookApiClient books = new BookApiClient();
 
    @Test
    void shouldReturnAllBooks() {
        Response response = books.getAll();
 
        assertEquals(200, response.statusCode());
        assertTrue(response.contentType().contains("application/json"));
 
        List<Map<String, Object>> result = response.jsonPath().getList("$");
        assertNotNull(result);
        assertFalse(result.isEmpty(), "Books response should not be empty");
        assertTrue(result.get(0).containsKey("id"));
        assertTrue(result.get(0).containsKey("title"));
    }
 
    @Test
    void shouldReturnBookByExistingId() {
        int id = firstBookId();
        Response response = books.getById(id);
 
        assertEquals(200, response.statusCode());
        assertEquals(id, response.jsonPath().getInt("id"));
        assertNotNull(response.jsonPath().getString("title"));
    }
 
    @Test
    void shouldCreateBook() {
        Map<String, Object> payload =
                TestData.validBook(0, "API Automation - New Book");
 
        Response response = books.create(payload);
 
        // OpenAPI documents 200 for Books POST but no response schema.
        assertEquals(200, response.statusCode());
    }
 
    @Test
    void shouldUpdateBook() {
        int id = firstBookId();
        String updatedTitle = "API Automation - Updated Book";
        Response response = books.update(
                id, TestData.validBook(id, updatedTitle));
 
        assertEquals(200, response.statusCode());
        assertEquals(id, response.jsonPath().getInt("id"));
        assertEquals(updatedTitle, response.jsonPath().getString("title"));
    }
 
    @Test
    void shouldDeleteBook() {
        int id = firstBookId();
        Response response = books.delete(id);
 
        // FakeRESTAPI simulates deletion; do not verify persistent state with GET.
        assertEquals(200, response.statusCode());
    }
 
    @Test
    void shouldReturnNotFoundForUnknownBookId() {
        Response response = books.getById(999999);
 
        assertEquals(404, response.statusCode());
    }
 
    @Test
    void shouldRejectNonNumericBookId() {
        Response response = books.getById("not-a-number");
 
        assertTrue(Set.of(400, 404).contains(response.statusCode()),
                "Expected a client error for a non-numeric path id");
    }
 
    @Test
    void shouldRejectMalformedJsonWhenCreatingBook() {
        Response response = books.createRaw("{ invalid-json }");
 
        assertEquals(400, response.statusCode());
    }
 
    private int firstBookId() {
        Response response = books.getAll();
        assertEquals(200, response.statusCode());

        List<Map<String, Object>> result = response.jsonPath().getList("$");
        assertNotNull(result);
        assertFalse(result.isEmpty(), "Books response should not be empty");
        return ((Number) result.get(0).get("id")).intValue();
    }
}
