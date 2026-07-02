package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
        LOGGER.info("Inside main — SpringLearnApplication started successfully");

        // Call the display method after the app starts
        displayCountry();
    }

    // -------------------------------------------------------
    // displayCountry()
    // Loads country.xml from the classpath (src/main/resources),
    // retrieves the 'country' bean, and displays its details.
    // -------------------------------------------------------
    private static void displayCountry() {
        LOGGER.info("Start");

        // Load the Spring XML configuration file
        // ClassPathXmlApplicationContext looks for the file inside
        // src/main/resources on the classpath
        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");

        // Retrieve the 'country' bean by id — Spring has already
        // called the constructor and both setter methods at this point
        Country country = context.getBean("country", Country.class);

        // Debug log — uses Country.toString() to print the object
        LOGGER.debug("country={}", country);

        // Display all four countries from the XML config
        LOGGER.info("--- All Countries from country.xml ---");
        String[] ids = {"country", "countryUS", "countryDE", "countryJP"};
        for (String id : ids) {
            Country c = context.getBean(id, Country.class);
            LOGGER.info("Code: {} | Name: {}", c.getCode(), c.getName());
        }

        ((ClassPathXmlApplicationContext) context).close();
        LOGGER.info("End");
    }
}
