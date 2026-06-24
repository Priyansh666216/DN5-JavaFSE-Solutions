package com.example;

// Service layer that talks to an external API
// The ExternalApi is injected via constructor (Dependency Injection)
// This makes it easy to swap in a mock during testing
public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchUser(int id) {
        return externalApi.getUserById(id);
    }

    public String checkStatus() {
        boolean available = externalApi.isServiceAvailable();
        return available ? "Service is UP" : "Service is DOWN";
    }
}
