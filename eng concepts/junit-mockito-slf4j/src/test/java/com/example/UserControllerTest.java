package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.NoSuchElementException;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Spring Test - Exercise 3: Testing a REST Controller with MockMvc
// Spring Test - Exercise 5: Test Controller POST Endpoint
// Spring Test - Exercise 8: Test Controller Exception Handling
// Mock Dependencies - Exercise 1: Mocking a Service Dependency in a Controller Test
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    // Exercise 3: GET /users/{id} - user found
    @Test
    void testGetUser_returnsUser() throws Exception {
        User user = new User(1L, "Alice");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.name").value("Alice"));

        verify(userService).getUserById(1L);
    }

    // Exercise 5: POST /users - create user
    @Test
    void testCreateUser_returnsCreatedUser() throws Exception {
        User input  = new User(null, "Bob");
        User saved  = new User(2L, "Bob");
        when(userService.saveUser(any(User.class))).thenReturn(saved);

        mockMvc.perform(post("/users")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsString(input)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(2))
               .andExpect(jsonPath("$.name").value("Bob"));
    }

    // Exercise 8: GET /users/{id} - not found triggers GlobalExceptionHandler
    @Test
    void testGetUser_notFound_returns404() throws Exception {
        when(userService.getUserById(99L))
            .thenThrow(new NoSuchElementException("User not found"));

        mockMvc.perform(get("/users/99"))
               .andExpect(status().isNotFound())
               .andExpect(content().string("User not found"));
    }
}
