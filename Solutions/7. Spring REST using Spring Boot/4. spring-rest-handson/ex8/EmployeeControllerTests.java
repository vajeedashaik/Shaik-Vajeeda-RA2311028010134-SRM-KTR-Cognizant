package com.cognizant.springlearn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

// Exercise 8, step 6: Include MockMvc test for the exceptional scenario
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mvc;

    // PUT /employees with an id that does not exist should throw EmployeeNotFoundException -> 404
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUpdateEmployeeException() throws Exception {
        String nonExistentEmployee = "{\"id\":999,\"name\":\"Test User\",\"salary\":50000.0,"
                + "\"permanent\":true,\"dateOfBirth\":\"01/01/1990\"}";
        ResultActions actions = mvc.perform(put("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nonExistentEmployee));
        actions.andExpect(status().isNotFound());
        actions.andExpect(status().reason("Employee not found"));
    }
}
