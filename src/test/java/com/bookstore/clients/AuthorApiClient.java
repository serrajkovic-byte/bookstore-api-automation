package com.bookstore.clients;
 
import com.bookstore.config.ApiSpecifications;
import io.restassured.response.Response;
 
import java.util.Map;
 
import static io.restassured.RestAssured.given;
 
public class AuthorApiClient {
    public Response getAll() {
        return given().spec(ApiSpecifications.defaultRequest())
                .when().get("/Authors");
    }
 
    public Response getById(Object id) {
        return given().spec(ApiSpecifications.defaultRequest())
                .pathParam("id", id)
                .when().get("/Authors/{id}");
    }
 
    public Response create(Map<String, Object> payload) {
        return given().spec(ApiSpecifications.defaultRequest())
                .body(payload)
                .when().post("/Authors");
    }
 
    public Response update(int id, Map<String, Object> payload) {
        return given().spec(ApiSpecifications.defaultRequest())
                .pathParam("id", id)
                .body(payload)
                .when().put("/Authors/{id}");
    }
 
    public Response delete(int id) {
        return given().spec(ApiSpecifications.defaultRequest())
                .pathParam("id", id)
                .when().delete("/Authors/{id}");
    }
}
