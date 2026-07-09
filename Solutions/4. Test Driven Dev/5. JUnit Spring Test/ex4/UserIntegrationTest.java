package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.NoSuchElementException;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Spring Test - Exercise 4: Integration Test with Spring Boot
// Mock Dependencies - Exercise 3: Mocking a Service Dependency in an Integration Test
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    // Full controller → service flow (service mocked, full Spring context loaded)
    @Test
    void integrationTest_getUser_returnsUser() throws Exception {
        User user = new User(1L, "Integration User");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Integration User"));
    }

    @Test
    void integrationTest_postUser_returnsCreatedUser() throws Exception {
        User input = new User(null, "New User");
        User saved = new User(10L, "New User");
        when(userService.saveUser(any(User.class))).thenReturn(saved);

        mockMvc.perform(post("/users")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsString(input)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(10));
    }

    @Test
    void integrationTest_getUser_notFound_returns404() throws Exception {
        when(userService.getUserById(999L))
            .thenThrow(new NoSuchElementException("User not found"));

        mockMvc.perform(get("/users/999"))
               .andExpect(status().isNotFound());
    }
}
