package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @SuppressWarnings("unchecked")
    private List<Country> getCountryList() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        return context.getBean("countryList", List.class);
    }

    // Handson 2 (file 2): return India country
    public Country getCountryIndia() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        LOGGER.info("END");
        return country;
    }

    // Handson 2 (file 2): return all countries
    public List<Country> getAllCountries() {
        LOGGER.info("START");
        List<Country> list = getCountryList();
        LOGGER.info("END");
        return list;
    }

    // Handson 2 (file 2): find country by code (case insensitive)
    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START");
        List<Country> list = getCountryList();
        Country found = list.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(CountryNotFoundException::new);
        LOGGER.info("END");
        return found;
    }

    // Handson 4 (file 4): add country (in-memory, stateless for demo)
    public void addCountry(Country country) {
        LOGGER.info("START");
        LOGGER.debug("Adding country: {}", country);
        LOGGER.info("END");
    }

    // Handson 4 (file 4): update country
    public void updateCountry(Country country) throws CountryNotFoundException {
        LOGGER.info("START");
        getCountry(country.getCode()); // throws if not found
        LOGGER.debug("Updating country: {}", country);
        LOGGER.info("END");
    }

    // Handson 4 (file 4): delete country
    public void deleteCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START");
        getCountry(code); // throws if not found
        LOGGER.debug("Deleting country: {}", code);
        LOGGER.info("END");
    }
}
