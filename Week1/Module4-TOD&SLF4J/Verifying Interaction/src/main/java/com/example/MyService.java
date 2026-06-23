package com.example;

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

    public void notifyUser(String message) {
        externalApi.sendNotification(message);
    }

    public void updateAndNotify(int id, String value) {
        externalApi.updateRecord(id, value);
        externalApi.sendNotification("Record " + id + " updated");
    }
}
