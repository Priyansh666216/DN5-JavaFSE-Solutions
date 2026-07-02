package com.cognizant.springlearn.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * JwtUtil
 * Responsible for:
 *   1. Generating a signed JWT from a username
 *   2. Extracting claims (username, expiry) from a JWT
 *   3. Validating a JWT
 *
 * JWT Structure (3 parts separated by dots):
 *   eyJhbGciOiJIUzI1NiJ9      ← Header  (algorithm: HS256)
 *   .eyJzdWIiOiJ1c2VyIiwi...  ← Payload (sub=username, iat=issued, exp=expiry)
 *   .t3LRvlCV-hwKfoqZYla...   ← Signature (HMAC-SHA256 of header+payload using secret key)
 */
@Component
public class JwtUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    // Injected from application.properties
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.validity}")
    private long validity;

    // -------------------------------------------------------
    // Generate token for a given username
    // Called by AuthenticationController after verifying credentials
    // -------------------------------------------------------
    public String generateToken(String username) {
        LOGGER.debug("Generating token for username={}", username);

        Map<String, Object> claims = new HashMap<>();

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)                          // "sub" claim
                .setIssuedAt(new Date())                       // "iat" claim
                .setExpiration(new Date(System.currentTimeMillis() + validity)) // "exp" claim
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        LOGGER.debug("Token generated successfully");
        return token;
    }

    // -------------------------------------------------------
    // Extract username (subject) from token
    // -------------------------------------------------------
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // -------------------------------------------------------
    // Check if token has expired
    // -------------------------------------------------------
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // -------------------------------------------------------
    // Validate token — username matches and not expired
    // -------------------------------------------------------
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    // -------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        // HMAC-SHA key derived from the secret string in application.properties
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
