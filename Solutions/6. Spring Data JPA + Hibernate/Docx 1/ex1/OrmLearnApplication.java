package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;
    private static StockRepository stockRepository;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        countryService = context.getBean(CountryService.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);
        stockRepository = context.getBean(StockRepository.class);

        // Docx 1 - Hands on 1: get all countries
        testGetAllCountries();

        // Docx 1 - Hands on 6: find country by code
        testFindCountryByCode();

        // Docx 1 - Hands on 7: add country
        testAddCountry();

        // Docx 1 - Hands on 8: update country
        testUpdateCountry();

        // Docx 1 - Hands on 9: delete country
        testDeleteCountry();

        // Docx 2 - Hands on 1: query methods on country
        testCountryQueryMethods();

        // Docx 2 - Hands on 2: query methods on stock
        testStockQueryMethods();

        // Docx 2 - Hands on 4: many-to-one - get employee with department
        testGetEmployee();

        // Docx 2 - Hands on 4: add employee
        testAddEmployee();

        // Docx 2 - Hands on 4: update employee department
        testUpdateEmployee();

        // Docx 2 - Hands on 5: one-to-many - get department with employees
        testGetDepartment();

        // Docx 2 - Hands on 6: many-to-many - add skill to employee
        testAddSkillToEmployee();

        // Docx 3 - Hands on 2: HQL for permanent employees
        testGetAllPermanentEmployees();

        // Docx 3 - Hands on 4: HQL aggregate - average salary
        testGetAverageSalary();

        // Docx 3 - Hands on 5: native query - get all employees
        testGetAllEmployeesNative();
    }

    // ===================== Docx 1 Tests =====================

    private static void testGetAllCountries() {
        LOGGER.info("Start - testGetAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End - testGetAllCountries");
    }

    private static void testFindCountryByCode() {
        LOGGER.info("Start - testFindCountryByCode");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country:{}", country);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found: {}", e.getMessage());
        }
        LOGGER.info("End - testFindCountryByCode");
    }

    private static void testAddCountry() {
        LOGGER.info("Start - testAddCountry");
        Country country = new Country();
        country.setCode("ZZ");
        country.setName("Test Country");
        countryService.addCountry(country);

        try {
            Country added = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Added country:{}", added);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found after add: {}", e.getMessage());
        }
        LOGGER.info("End - testAddCountry");
    }

    private static void testUpdateCountry() {
        LOGGER.info("Start - testUpdateCountry");
        try {
            countryService.updateCountry("ZZ", "Updated Test Country");
            Country updated = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Updated country:{}", updated);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found: {}", e.getMessage());
        }
        LOGGER.info("End - testUpdateCountry");
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start - testDeleteCountry");
        countryService.deleteCountry("ZZ");
        try {
            countryService.findCountryByCode("ZZ");
            LOGGER.debug("Country still exists - unexpected");
        } catch (CountryNotFoundException e) {
            LOGGER.debug("Country deleted successfully - code ZZ not found (expected)");
        }
        LOGGER.info("End - testDeleteCountry");
    }

    // ===================== Docx 2 Tests =====================

    private static void testCountryQueryMethods() {
        LOGGER.info("Start - testCountryQueryMethods");

        // Search containing 'ou'
        List<Country> containingOu = countryService.searchByName("ou");
        LOGGER.debug("Countries containing 'ou': {}", containingOu);

        // Search containing 'ou' sorted ascending
        List<Country> containingOuSorted = countryService.searchByNameSorted("ou");
        LOGGER.debug("Countries containing 'ou' sorted: {}", containingOuSorted);

        // Countries starting with 'Z'
        List<Country> startingWithZ = countryService.getCountriesStartingWith("Z");
        LOGGER.debug("Countries starting with 'Z': {}", startingWithZ);

        LOGGER.info("End - testCountryQueryMethods");
    }

    private static void testStockQueryMethods() {
        LOGGER.info("Start - testStockQueryMethods");

        // FB stocks in September 2019
        List<Stock> fbSep2019 = stockRepository.findByCodeAndDateBetween(
            "FB",
            LocalDate.of(2019, 9, 1),
            LocalDate.of(2019, 9, 30)
        );
        LOGGER.debug("FB September 2019 stocks: {}", fbSep2019);

        // GOOGL close > 1250
        List<Stock> googlAbove1250 = stockRepository.findByCodeAndCloseGreaterThan(
            "GOOGL", new BigDecimal("1250")
        );
        LOGGER.debug("GOOGL stocks above 1250: {}", googlAbove1250);

        // Top 3 by volume
        List<Stock> top3Volume = stockRepository.findTop3ByOrderByVolumeDesc();
        LOGGER.debug("Top 3 by volume: {}", top3Volume);

        // 3 lowest NFLX close prices
        List<Stock> lowestNflx = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        LOGGER.debug("3 lowest NFLX stocks: {}", lowestNflx);

        LOGGER.info("End - testStockQueryMethods");
    }

    private static void testGetEmployee() {
        LOGGER.info("Start - testGetEmployee");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.info("End - testGetEmployee");
    }

    private static void testAddEmployee() {
        LOGGER.info("Start - testAddEmployee");
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setSalary(75000.00);
        employee.setPermanent(true);
        employee.setDateOfBirth(new Date());

        Department department = departmentService.get(1);
        employee.setDepartment(department);

        employeeService.save(employee);
        LOGGER.debug("Added employee:{}", employee);
        LOGGER.info("End - testAddEmployee");
    }

    private static void testUpdateEmployee() {
        LOGGER.info("Start - testUpdateEmployee");
        Employee employee = employeeService.get(1);
        Department department = departmentService.get(2);
        employee.setDepartment(department);
        employeeService.save(employee);
        LOGGER.debug("Updated employee:{}", employee);
        LOGGER.info("End - testUpdateEmployee");
    }

    private static void testGetDepartment() {
        LOGGER.info("Start - testGetDepartment");
        Department department = departmentService.get(1);
        LOGGER.debug("Department:{}", department);
        LOGGER.debug("Employees:{}", department.getEmployeeList());
        LOGGER.info("End - testGetDepartment");
    }

    private static void testAddSkillToEmployee() {
        LOGGER.info("Start - testAddSkillToEmployee");
        Employee employee = employeeService.get(1);
        Skill skill = skillService.get(3);
        employee.getSkillList().add(skill);
        employeeService.save(employee);
        LOGGER.debug("Employee after adding skill:{}", employee);
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.info("End - testAddSkillToEmployee");
    }

    // ===================== Docx 3 Tests =====================

    private static void testGetAllPermanentEmployees() {
        LOGGER.info("Start - testGetAllPermanentEmployees");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("End - testGetAllPermanentEmployees");
    }

    private static void testGetAverageSalary() {
        LOGGER.info("Start - testGetAverageSalary");
        double avgSalary = employeeService.getAverageSalary(1);
        LOGGER.debug("Average salary for department 1: {}", avgSalary);
        LOGGER.info("End - testGetAverageSalary");
    }

    private static void testGetAllEmployeesNative() {
        LOGGER.info("Start - testGetAllEmployeesNative");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        LOGGER.debug("All employees (native): {}", employees);
        LOGGER.info("End - testGetAllEmployeesNative");
    }
}
