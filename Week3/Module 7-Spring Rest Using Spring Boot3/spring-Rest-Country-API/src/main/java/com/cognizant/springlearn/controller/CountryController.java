package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * @RestController
 *   Combines @Controller + @ResponseBody.
 *   Every method return value is written directly into the HTTP response body.
 *
 * HOW BEAN → JSON CONVERSION WORKS (SME Note):
 *   When this method returns a Country object, Spring does NOT send the
 *   Java object directly. Instead:
 *     1. Spring sees the return type is an Object (not a String).
 *     2. It looks for a HttpMessageConverter on the classpath.
 *     3. spring-boot-starter-web auto-configures Jackson (MappingJackson2HttpMessageConverter).
 *     4. Jackson reads the Country object's getter methods:
 *          getCode() → "IN"
 *          getName() → "India"
 *     5. Jackson serializes this into: {"code":"IN","name":"India"}
 *     6. Spring sets Content-Type: application/json in the response header automatically.
 *   No extra configuration needed — Jackson is on the classpath via spring-boot-starter-web.
 */
@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    /*
     * @RequestMapping
     *   Maps HTTP requests to this method.
     *   value  = "/country"      → the URL path
     *   method = RequestMethod.GET → only handles GET requests
     *
     * WHAT HAPPENS IN THIS METHOD (SME Note):
     *   1. Browser/Postman sends: GET http://localhost:8083/country
     *   2. DispatcherServlet receives the request.
     *   3. It finds this method matches the URL + HTTP method.
     *   4. Spring calls getCountryIndia().
     *   5. Inside, we load country.xml and get the "country" bean (India).
     *   6. We return the Country object.
     *   7. Jackson converts it to JSON: {"code":"IN","name":"India"}
     *   8. Spring sends HTTP 200 OK with that JSON as the response body.
     */
    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public Country getCountryIndia() {
        LOGGER.info("Start");

        // Load the Spring XML configuration from classpath (src/main/resources)
        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");

        // Retrieve the 'country' bean — Spring has already called the
        // constructor and setCode("IN"), setName("India") at this point
        Country country = context.getBean("country", Country.class);

        LOGGER.debug("country={}", country);
        LOGGER.info("End");

        // Jackson automatically converts this Country object to:
        // {"code":"IN","name":"India"}
        return country;
    }
}
