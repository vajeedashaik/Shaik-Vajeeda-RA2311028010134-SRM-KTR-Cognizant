package com.library.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Exercise 1: Define BookRepository in com.library.repository
// Exercise 6: @Repository annotation (component scanning)
@Repository
public class BookRepository {

    private final List<String> books = new ArrayList<>();

    public void save(String title) {
        books.add(title);
        System.out.println("[BookRepository] Saved book: " + title);
    }

    public List<String> findAll() {
        System.out.println("[BookRepository] Fetching all books");
        return new ArrayList<>(books);
    }

    public Optional<String> findByTitle(String title) {
        return books.stream().filter(b -> b.equalsIgnoreCase(title)).findFirst();
    }

    public void deleteByTitle(String title) {
        books.removeIf(b -> b.equalsIgnoreCase(title));
        System.out.println("[BookRepository] Deleted book: " + title);
    }
}
