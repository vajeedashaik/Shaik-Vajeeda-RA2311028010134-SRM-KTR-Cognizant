package com.cognizant.springlearn.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

// Handson 2 (file 2): Country REST services
// Handson 4 (file 4): POST/PUT/DELETE with validation
@RestController
@RequestMapping("/countries")
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    public CountryController() {
        LOGGER.debug("Inside CountryController constructor.");
    }

    // GET /countries - get all countries (Handson: REST - Get all countries)
    @GetMapping
    public List<Country> getAllCountries() {
        LOGGER.info("START");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.info("END");
        return countries;
    }

    // GET /country - get India country (legacy single endpoint)
    @GetMapping("/india")
    public Country getCountryIndia() {
        LOGGER.info("START");
        Country country = countryService.getCountryIndia();
        LOGGER.info("END");
        return country;
    }

    // GET /countries/{code} - get specific country by code (Handson: REST - Get country based on country code)
    @GetMapping("/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("START");
        Country country = countryService.getCountry(code);
        LOGGER.info("END");
        return country;
    }

    // POST /countries - add country (Handson 4: Create RESTful Web Service for POST)
    @PostMapping
    public Country addCountry(@RequestBody @Valid Country country) {
        LOGGER.info("START");
        LOGGER.debug("Country: {}", country);
        countryService.addCountry(country);
        LOGGER.info("END");
        return country;
    }

    // PUT /countries - update country (Handson 4)
    @PutMapping
    public void updateCountry(@RequestBody @Valid Country country) throws CountryNotFoundException {
        LOGGER.info("START");
        LOGGER.debug("Country: {}", country);
        countryService.updateCountry(country);
        LOGGER.info("END");
    }

    // DELETE /countries/{code} - delete country (Handson 4)
    @DeleteMapping("/{code}")
    public void deleteCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("START");
        countryService.deleteCountry(code);
        LOGGER.info("END");
    }
}
