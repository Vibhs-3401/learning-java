package com.register.learning.constants;

public class SecurityConstants {

    public static final String JWT_AUTHORIZATION_TOKEN = "Authorization";

    public static final String ALLOWED_URLS[] = {
            "/swagger-resources/**",
            "/swagger-resources",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/v2/api-docs",
            "/webjars/**",
            "/sign-in",
            "/sign-up",
            "/public/**"
    };
}
