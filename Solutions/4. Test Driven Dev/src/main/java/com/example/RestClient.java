package com.example;

public interface RestClient {
    String getResponse();
    String post(String endpoint, String body);
}
