package com.cognizant.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Docx 4 - Hands on 9: two DataSource/EntityManagerFactory/TransactionManager beans are wired
// manually (see primary/config and audit/config). Spring Boot's default single-datasource
// auto-configuration (DataSourceAutoConfiguration / HibernateJpaAutoConfiguration) still runs,
// but backs off automatically because beans of those types already exist - it is also the
// source of the EntityManagerFactoryBuilder bean the manual config classes depend on.
@SpringBootApplication
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }
}
