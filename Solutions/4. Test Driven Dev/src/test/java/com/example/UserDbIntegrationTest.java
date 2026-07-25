package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Spring Test - Exercise 4: Integration Test with Spring Boot
// No mocks anywhere: exercises the real controller -> service -> repository -> H2 database.
// (Compare with UserIntegrationTest, which is Mock Dependencies - Exercise 3 and
// deliberately mocks UserService instead.)
@SpringBootTest
@AutoConfigureMockMvc
public class UserDbIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void fullFlow_postThenGet_persistsAndReadsBackFromRealDatabase() throws Exception {
        User input = new User(null, "DB Integration User");

        String responseBody = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        User created = objectMapper.readValue(responseBody, User.class);
        assertNotNull(created.getId());

        // Prove persistence actually happened, not just that the endpoint echoed a value.
        assertTrue(userRepository.findById(created.getId()).isPresent());

        mockMvc.perform(get("/users/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("DB Integration User"));
    }
}
