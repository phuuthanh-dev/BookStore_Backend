package vn.bookstore.backend.security;

public class Endpoints {
    public static final String front_end_host = "http://localhost:3000";

    public static final String[] PUBLIC_GET_ENDPOINTS = {
            "/books", "/books/**", "/images", "/images/**", "/categories", "categories/**"
            , "/ratings", "/ratings/**"
            , "/users/search/existsByUsername"
            , "/users/search/existsByEmail"
            , "/api/user/active"
    };

    public static final String[] PUBLIC_POST_ENDPOINTS = {
            "/api/auth/register", "/api/auth/login", "/api/book/add"
    };

    public static final String[] ADMIN_GET_ENDPOINTS = {
            "/users", "users/**", "/api/book/**", "/api/user/**"
    };

    public static final String[] ADMIN_POST_ENDPOINTS = {
            "/books"
    };
}
