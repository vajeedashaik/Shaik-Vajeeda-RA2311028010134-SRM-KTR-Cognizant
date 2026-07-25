package com.cognizant.springlearn.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// Exercise 1: Securing RESTful Web Services with Spring Security
// Enabling this (with no overrides) secures every endpoint with HTTP Basic auth and an
// auto-generated password that Spring Boot prints to the console log at startup.
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
}
