package com.example;

public interface ExternalApi {

    String getData();

    String getUserById(int id);

    void sendNotification(String message);

    void updateRecord(int id, String value);
}
