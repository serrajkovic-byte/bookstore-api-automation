package com.bookstore.testdata;
 
import java.util.LinkedHashMap;
import java.util.Map;
 
public final class TestData {
    private TestData() {
    }
 
    public static Map<String, Object> validBook(int id, String title) {
        Map<String, Object> book = new LinkedHashMap<>();
        book.put("id", id);
        book.put("title", title);
        book.put("description", "Created for API automation assessment");
        book.put("pageCount", 250);
        book.put("excerpt", "Sample excerpt used by automated tests.");
        book.put("publishDate", "2026-11-06T10:00:00Z");
        return book;
    }
 
    public static Map<String, Object> validAuthor(
            int id, int idBook, String firstName, String lastName) {
        Map<String, Object> author = new LinkedHashMap<>();
        author.put("id", id);
        author.put("idBook", idBook);
        author.put("firstName", firstName);
        author.put("lastName", lastName);
        return author;
    }
}
