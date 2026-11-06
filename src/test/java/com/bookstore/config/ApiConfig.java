package com.bookstore.config;
 
public final class ApiConfig {
    private static final String DEFAULT_BASE_URL =
            "https://fakerestapi.azurewebsites.net/api/v1";
 
    private ApiConfig() {
        // Utility class - prevent instantiation.
    }
 
    public static String baseUrl() {
        String configuredUrl = System.getProperty(
                "api.baseUrl",
                System.getenv().getOrDefault("API_BASE_URL", DEFAULT_BASE_URL)
        );
 
        String cleanUrl = configuredUrl.trim();
        if (cleanUrl.endsWith("/")) {
            cleanUrl = cleanUrl.substring(0, cleanUrl.length() - 1);
        }
        return cleanUrl;
    }
}
