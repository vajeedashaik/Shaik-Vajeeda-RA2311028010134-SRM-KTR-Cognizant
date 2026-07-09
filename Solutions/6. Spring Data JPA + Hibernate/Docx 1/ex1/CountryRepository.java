package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Docx 2 - Hands on 1: search containing text
    List<Country> findByNameContaining(String text);

    // Docx 2 - Hands on 1: search containing text, sorted ascending
    List<Country> findByNameContainingOrderByNameAsc(String text);

    // Docx 2 - Hands on 1: starts with letter
    List<Country> findByNameStartingWith(String letter);
}
