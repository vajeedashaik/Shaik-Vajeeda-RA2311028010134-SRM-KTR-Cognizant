package com.cognizant.springlearn;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cognizant.springlearn.controller.CountryController;

// Handson 2 (file 2): MockMVC tests for country REST service
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringLearnApplicationTests {

    @Autowired
    private CountryController countryController;

    @Autowired
    private MockMvc mvc;

    // Test: CountryController bean is loaded (context loads successfully)
    @Test
    public void contextLoads() {
        assertNotNull(countryController);
    }

    // Test: GET /countries returns 200 with code "IN" and name "India"
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetCountry() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/in"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").exists());
        actions.andExpect(jsonPath("$.code").value("IN"));
        actions.andExpect(jsonPath("$.name").exists());
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    // Test: GET /countries/{code} with invalid code returns 404
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetCountryException() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/az"));
        actions.andExpect(status().isNotFound());
        actions.andExpect(status().reason("Country not found"));
    }

    // Test: GET /countries returns list of all countries
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetAllCountries() throws Exception {
        ResultActions actions = mvc.perform(get("/countries"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$").isArray());
        actions.andExpect(jsonPath("$[0].code").exists());
    }

    // Test: GET /hello returns Hello World!!
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testSayHello() throws Exception {
        ResultActions actions = mvc.perform(get("/hello"));
        actions.andExpect(status().isOk());
    }
}
