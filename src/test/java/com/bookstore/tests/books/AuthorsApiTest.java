package com.bookstore.tests.books;
 
import com.bookstore.clients.AuthorApiClient;
import com.bookstore.testdata.TestData;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
 
import java.util.List;
import java.util.Map;
 
import static org.junit.jupiter.api.Assertions.*;
 
class AuthorsApiTest {
    private final AuthorApiClient authors = new AuthorApiClient();
 
    @Test
    void shouldReturnAllAuthors() {
        Response response = authors.getAll();
 
        assertEquals(200, response.statusCode());
        List<Map<String, Object>> result = response.jsonPath().getList("$");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.get(0).containsKey("firstName"));
        assertTrue(result.get(0).containsKey("lastName"));
    }
 
    @Test
    void shouldReturnAuthorByExistingId() {
        int id = firstAuthorId();
        Response response = authors.getById(id);
 
        assertEquals(200, response.statusCode());
        assertEquals(id, response.jsonPath().getInt("id"));
    }
 
    @Test
    void shouldCreateAuthor() {
        Map<String, Object> payload =
                TestData.validAuthor(0, 1, "Alex", "Tester");
        Response response = authors.create(payload);
 
        assertEquals(200, response.statusCode());
        assertEquals("Alex", response.jsonPath().getString("firstName"));
        assertEquals("Tester", response.jsonPath().getString("lastName"));
    }
 
    @Test
    void shouldUpdateAuthor() {
        int id = firstAuthorId();
        Map<String, Object> payload =
                TestData.validAuthor(id, 1, "Updated", "Author");
        Response response = authors.update(id, payload);
 
        assertEquals(200, response.statusCode());
        assertEquals(id, response.jsonPath().getInt("id"));
        assertEquals("Updated", response.jsonPath().getString("firstName"));
    }
 
    @Test
    void shouldDeleteAuthor() {
        int id = firstAuthorId();
        Response response = authors.delete(id);
 
        assertEquals(200, response.statusCode());
    }
 
    private int firstAuthorId() {
        Response listResponse = authors.getAll();
        assertEquals(200, listResponse.statusCode());
        Integer id = listResponse.jsonPath().getInt("[0].id");
        assertNotNull(id, "The first author should contain an id");
        return id;
    }
}
