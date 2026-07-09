package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // Docx 1 - Hands on 1: get all countries
    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Docx 1 - Hands on 6: find by code
    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found for code: " + countryCode);
        }
        return result.get();
    }

    // Docx 1 - Hands on 7: add country
    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    // Docx 1 - Hands on 8: update country name by code
    @Transactional
    public void updateCountry(String code, String name) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(code);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found for code: " + code);
        }
        Country country = result.get();
        country.setName(name);
        countryRepository.save(country);
    }

    // Docx 1 - Hands on 9: delete by code
    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    // Docx 2 - Hands on 1: search containing text
    @Transactional
    public List<Country> searchByName(String text) {
        return countryRepository.findByNameContaining(text);
    }

    // Docx 2 - Hands on 1: search containing text sorted ascending
    @Transactional
    public List<Country> searchByNameSorted(String text) {
        return countryRepository.findByNameContainingOrderByNameAsc(text);
    }

    // Docx 2 - Hands on 1: starts with letter
    @Transactional
    public List<Country> getCountriesStartingWith(String letter) {
        return countryRepository.findByNameStartingWith(letter);
    }
}
