package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.JwtTokenResponse;
import com.cognizant.springlearn.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/*
 * AuthenticationController
 *
 * Handles: GET /authenticate
 *
 * STEP 1 — curl sends the request with -u user:pwd
 *   curl -s -u user:pwd http://localhost:8090/authenticate
 *   This adds the header: Authorization: Basic dXNlcjpwd2Q=
 *   where dXNlcjpwd2Q= is Base64("user:pwd")
 *
 * STEP 2 — Read and decode the Authorization header
 *   Extract "dXNlcjpwd2Q=" from the header value
 *   Base64-decode it to get "user:pwd"
 *   Split on ":" to get username="user" and password="pwd"
 *
 * STEP 3 — Generate JWT based on the username
 *   Pass the username to JwtUtil.generateToken()
 *   Return the token wrapped in JwtTokenResponse
 *   Jackson serializes it to: {"token":"eyJhbGci..."}
 */
@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/authenticate")
    public JwtTokenResponse authenticate(
            @RequestHeader("Authorization") String authorizationHeader) {

        LOGGER.info("Start");
        LOGGER.debug("Authorization header received={}", authorizationHeader);

        // -------------------------------------------------------
        // STEP 2: Read and decode the Authorization header
        //
        // Header format: "Basic dXNlcjpwd2Q="
        //   "Basic " prefix is 6 characters — remove it to get the Base64 string
        // -------------------------------------------------------
        String base64Credentials = authorizationHeader.substring("Basic ".length());
        LOGGER.debug("Base64 credentials={}", base64Credentials);

        // Decode Base64 → "user:pwd"
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String decodedCredentials = new String(decodedBytes, StandardCharsets.UTF_8);
        LOGGER.debug("Decoded credentials={}", decodedCredentials);

        // Split "user:pwd" into username and password
        String[] parts = decodedCredentials.split(":", 2);
        if (parts.length != 2) {
            throw new BadCredentialsException("Invalid Authorization header format");
        }

        String username = parts[0];
        String password = parts[1];
        LOGGER.debug("username={}", username);

        // -------------------------------------------------------
        // STEP 3: Generate token based on the username
        //
        // Note: Spring Security has already verified the credentials
        // before this method runs (because SecurityConfig has httpBasic()
        // and the user exists in the InMemoryUserDetailsManager).
        // So we only reach here if credentials are valid.
        // -------------------------------------------------------
        String token = jwtUtil.generateToken(username);

        LOGGER.debug("Token generated for username={}", username);
        LOGGER.info("End");

        // Jackson serializes this to: {"token":"eyJhbGci..."}
        return new JwtTokenResponse(token);
    }
}
