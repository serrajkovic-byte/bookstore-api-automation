package com.bookstore.config;
 
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
 
public final class ApiSpecifications {
    private ApiSpecifications() {
    }
 
    public static RequestSpecification defaultRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(ApiConfig.baseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }
}
