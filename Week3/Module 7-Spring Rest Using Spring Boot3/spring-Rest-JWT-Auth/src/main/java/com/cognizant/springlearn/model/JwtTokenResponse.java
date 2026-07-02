package com.cognizant.springlearn.model;

/*
 * Simple wrapper for the JWT token string.
 * Jackson serializes this to: {"token":"eyJhbGci..."}
 */
public class JwtTokenResponse {

    private final String token;

    public JwtTokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
