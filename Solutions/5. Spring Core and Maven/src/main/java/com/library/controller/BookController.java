package com.library.controller;

import com.library.model.Book;
import com.library.repository.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Exercise 9: REST Controller for CRUD operations on Book
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    // GET all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookJpaRepository.findAll();
    }

    // GET book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookJpaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookJpaRepository.save(book);
    }

    // PUT update book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,
                                           @RequestBody Book updated) {
        return bookJpaRepository.findById(id).map(book -> {
            book.setTitle(updated.getTitle());
            book.setAuthor(updated.getAuthor());
            book.setIsbn(updated.getIsbn());
            return ResponseEntity.ok(bookJpaRepository.save(book));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookJpaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // GET books by author
    @GetMapping("/author/{author}")
    public List<Book> getByAuthor(@PathVariable String author) {
        return bookJpaRepository.findByAuthor(author);
    }

    // GET search by title keyword
    @GetMapping("/search")
    public List<Book> search(@RequestParam String keyword) {
        return bookJpaRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
