package com.library;

import com.library.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// Exercise 1: Main class to load Spring context and test configuration
// Exercise 9: Spring Boot Application entry point
@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        // Exercise 9: Spring Boot - starts embedded server on port 8080
        SpringApplication.run(LibraryManagementApplication.class, args);
    }

    // -------------------------------------------------------
    // Exercise 1, 2, 5: Load classic XML-based Spring context
    // Run this method independently to test XML config
    // -------------------------------------------------------
    public static void runXmlConfigDemo() {
        System.out.println("=== Exercise 1/2/5: XML Config Demo ===");
        ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = context.getBean("bookService", BookService.class);

        bookService.addBook("Clean Code");
        bookService.addBook("Effective Java");
        System.out.println("All books: " + bookService.getAllBooks());

        ((ClassPathXmlApplicationContext) context).close();
    }

    // -------------------------------------------------------
    // Exercise 6: Annotation-based config demo (component scan)
    // -------------------------------------------------------
    public static void runAnnotationConfigDemo() {
        System.out.println("=== Exercise 6: Annotation Config Demo ===");
        ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = context.getBean(BookService.class);
        bookService.addBook("Spring in Action");
        System.out.println("Books: " + bookService.getAllBooks());

        ((ClassPathXmlApplicationContext) context).close();
    }
}
