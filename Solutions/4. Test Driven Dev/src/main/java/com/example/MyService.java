package com.example;

public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public void sendData(String data) {
        externalApi.sendData(data);
    }

    public String processRequest(String request) {
        return externalApi.processRequest(request);
    }
}
