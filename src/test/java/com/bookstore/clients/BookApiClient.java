package com.bookstore.clients;
 
import com.bookstore.config.ApiSpecifications;
import io.restassured.response.Response;
 
import java.util.Map;
 
import static io.restassured.RestAssured.given;
 
public class BookApiClient {
    public Response getAll() {
        return given()
                .spec(ApiSpecifications.defaultRequest())
                .when()
                .get("/Books");
    }
 
    public Response getById(Object id) {
        return given()
                .spec(ApiSpecifications.defaultRequest())
                .pathParam("id", id)
                .when()
                .get("/Books/{id}");
    }
 
    public Response create(Map<String, Object> payload) {
        return given()
                .spec(ApiSpecifications.defaultRequest())
                .body(payload)
                .when()
                .post("/Books");
    }
 
    public Response createRaw(String jsonBody) {
        return given()
                .spec(ApiSpecifications.defaultRequest())
                .body(jsonBody)
                .when()
                .post("/Books");
    }
 
    public Response update(int id, Map<String, Object> payload) {
        return given()
                .spec(ApiSpecifications.defaultRequest())
                .pathParam("id", id)
                .body(payload)
                .when()
                .put("/Books/{id}");
    }
 
    public Response delete(int id) {
        return given()
                .spec(ApiSpecifications.defaultRequest())
                .pathParam("id", id)
                .when()
                .delete("/Books/{id}");
    }
}
