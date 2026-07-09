package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// Exercise 1: Define BookService in com.library.service
// Exercise 2: Dependency Injection - setter injection
// Exercise 6: @Service annotation (component scanning)
// Exercise 7: Constructor AND setter injection
@Service
public class BookService {

    private BookRepository bookRepository;

    // Exercise 7: Constructor Injection
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] Created via constructor injection");
    }

    // Exercise 2, 7: Setter Injection
    // (also used by XML config via <property name="bookRepository" ref="bookRepository"/>)
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] BookRepository set via setter injection");
    }

    public void addBook(String title) {
        System.out.println("[BookService] Adding book: " + title);
        bookRepository.save(title);
    }

    public List<String> getAllBooks() {
        System.out.println("[BookService] Getting all books");
        return bookRepository.findAll();
    }

    public boolean bookExists(String title) {
        return bookRepository.findByTitle(title).isPresent();
    }

    public void removeBook(String title) {
        System.out.println("[BookService] Removing book: " + title);
        bookRepository.deleteByTitle(title);
    }
}
