package com.example;

public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        String raw = repository.getData();
        return "Processed " + raw;
    }
}
