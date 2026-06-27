package com.library;

import com.library.model.Book;
import com.library.repository.BookJpaRepository;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// =====================================================
// Exercise 1-8: Unit tests for BookService (no Spring)
// =====================================================
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    // Exercise 1/2: verify DI wired correctly
    @Test
    void testAddBook_callsRepository() {
        bookService.addBook("Clean Code");
        verify(bookRepository).save("Clean Code");
    }

    @Test
    void testGetAllBooks_returnsRepositoryResult() {
        when(bookRepository.findAll()).thenReturn(List.of("Clean Code", "Effective Java"));

        List<String> books = bookService.getAllBooks();

        assertEquals(2, books.size());
        verify(bookRepository).findAll();
    }

    @Test
    void testBookExists_found() {
        when(bookRepository.findByTitle("Clean Code"))
            .thenReturn(Optional.of("Clean Code"));

        assertTrue(bookService.bookExists("Clean Code"));
    }

    @Test
    void testBookExists_notFound() {
        when(bookRepository.findByTitle("Unknown")).thenReturn(Optional.empty());

        assertFalse(bookService.bookExists("Unknown"));
    }

    @Test
    void testRemoveBook_callsRepository() {
        bookService.removeBook("Clean Code");
        verify(bookRepository).deleteByTitle("Clean Code");
    }

    // Exercise 7: setter injection works
    @Test
    void testSetterInjection_replacesRepository() {
        BookRepository newRepo = mock(BookRepository.class);
        when(newRepo.findAll()).thenReturn(List.of("New Book"));

        bookService.setBookRepository(newRepo);
        List<String> result = bookService.getAllBooks();

        assertEquals(1, result.size());
        assertEquals("New Book", result.get(0));
    }
}

// =====================================================
// Exercise 9: Integration tests for REST controller
// =====================================================
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookJpaRepository bookJpaRepository;

    @Test
    void testGetAllBooks() throws Exception {
        when(bookJpaRepository.findAll())
            .thenReturn(List.of(new Book("Clean Code", "Robert Martin", "978-0132350884")));

        mockMvc.perform(get("/api/books"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].title").value("Clean Code"));
    }

    @Test
    void testGetBookById_found() throws Exception {
        Book book = new Book("Effective Java", "Joshua Bloch", "978-0134685991");
        book.setId(1L);
        when(bookJpaRepository.findById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.author").value("Joshua Bloch"));
    }

    @Test
    void testGetBookById_notFound() throws Exception {
        when(bookJpaRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/99"))
               .andExpect(status().isNotFound());
    }

    @Test
    void testCreateBook() throws Exception {
        Book saved = new Book("Spring in Action", "Craig Walls", "978-1617294945");
        saved.setId(1L);
        when(bookJpaRepository.save(any(Book.class))).thenReturn(saved);

        mockMvc.perform(post("/api/books")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content("{\"title\":\"Spring in Action\",\"author\":\"Craig Walls\",\"isbn\":\"978-1617294945\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testDeleteBook_noContent() throws Exception {
        when(bookJpaRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookJpaRepository).deleteById(1L);

        mockMvc.perform(delete("/api/books/1"))
               .andExpect(status().isNoContent());
    }
}
