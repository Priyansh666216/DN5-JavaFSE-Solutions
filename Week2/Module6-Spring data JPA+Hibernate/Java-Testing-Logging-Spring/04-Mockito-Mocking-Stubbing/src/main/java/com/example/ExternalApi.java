package com.example;

// Represents an external API (e.g. a REST client, third-party SDK, etc.)
// In production this would make real HTTP calls — we mock it in tests
public interface ExternalApi {

    String getData();

    String getUserById(int id);

    boolean isServiceAvailable();
}
