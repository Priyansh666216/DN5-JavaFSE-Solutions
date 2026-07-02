package com.cognizant.springlearn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/*
 * SecurityConfig
 *
 * Configures Spring Security for the application:
 *   1. Permits all requests to /authenticate (no login required to get a token)
 *   2. Requires authentication for all other endpoints
 *   3. Disables CSRF (not needed for stateless REST APIs)
 *   4. Sets session management to STATELESS (JWT handles state, not server sessions)
 *   5. Enables HTTP Basic Auth — so curl -u user:pwd sends an Authorization header
 *   6. Defines an in-memory user: username=user, password=pwd
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        LOGGER.debug("Configuring SecurityFilterChain");

        http
            // Disable CSRF — REST APIs are stateless, CSRF protection is for browser sessions
            .csrf().disable()

            // URL authorization rules
            .authorizeRequests()
                // /authenticate is public — anyone can call it to get a token
                .antMatchers("/authenticate").permitAll()
                // All other URLs require a valid authenticated user
                .anyRequest().authenticated()
            .and()

            // Stateless session — Spring will NOT create HTTP sessions
            // Each request must carry credentials (Basic Auth here, JWT later)
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            // Enable HTTP Basic Authentication
            // This allows curl -u user:pwd to send: Authorization: Basic dXNlcjpwd2Q=
            .httpBasic();

        return http.build();
    }

    /*
     * In-memory user store — single user for this hands-on.
     * username : user
     * password : pwd
     * role     : USER
     *
     * In a real application this would load from a database.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("pwd"))
                .roles("USER")
                .build();

        LOGGER.debug("In-memory user created: username=user");
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt is the recommended password hashing algorithm
        return new BCryptPasswordEncoder();
    }
}
