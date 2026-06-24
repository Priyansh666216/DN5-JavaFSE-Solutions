package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

// @SpringBootApplication is a shortcut for three annotations combined:
//   @Configuration       — marks this as a source of Spring bean definitions
//   @EnableAutoConfiguration — tells Spring Boot to auto-configure based on classpath
//   @ComponentScan       — scans this package (and sub-packages) for @Component, @Service, @Repository etc.
@SpringBootApplication
public class OrmLearnApplication {

    // SLF4J logger — uses Logback underneath (pulled in by spring-boot-starter-data-jpa)
    // The class name OrmLearnApplication becomes the logger name in log output
    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    // Static reference — set from ApplicationContext after Spring boots up
    private static CountryService countryService;

    public static void main(String[] args) {

        // SpringApplication.run() boots the entire Spring context:
        //   1. Reads application.properties
        //   2. Creates all beans (@Service, @Repository, etc.)
        //   3. Wires dependencies (@Autowired)
        //   4. Connects to the database via Hibernate
        // Returns the ApplicationContext so we can pull beans out manually
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

        // Retrieve the CountryService bean from the context
        countryService = context.getBean(CountryService.class);

        LOGGER.info("Inside main");

        // Run the test method
        testGetAllCountries();
    }

    // -------------------------------------------------------
    // Test method — simulates what a unit test would verify
    // -------------------------------------------------------
    private static void testGetAllCountries() {
        LOGGER.info("Start");

        List<Country> countries = countryService.getAllCountries();

        // LOGGER.debug uses {} as placeholder — avoids string concatenation cost
        // Output only visible because logging.level.com.cognizant=debug in properties
        LOGGER.debug("countries={}", countries);

        LOGGER.info("End");
    }
}
