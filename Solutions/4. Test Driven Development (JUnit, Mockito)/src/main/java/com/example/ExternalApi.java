package com.example;

public interface ExternalApi {
    String getData();
    void sendData(String data);
    String processRequest(String request);
}
