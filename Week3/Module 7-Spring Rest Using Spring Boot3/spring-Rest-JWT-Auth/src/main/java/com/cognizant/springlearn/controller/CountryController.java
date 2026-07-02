package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    // Spring injects the CountryService bean automatically via @Autowired
    @Autowired
    private CountryService countryService;

    // -------------------------------------------------------
    // GET /country — returns India (existing from previous hands-on)
    // -------------------------------------------------------
    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public Country getCountryIndia() {
        LOGGER.info("Start");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);

        LOGGER.debug("country={}", country);
        LOGGER.info("End");

        return country;
    }

    // -------------------------------------------------------
    // GET /countries/{code} — returns country matching the code
    //
    // @GetMapping("/countries/{code}")
    //   {code} is a path variable — the value in the URL after /countries/
    //   e.g. /countries/in  → code = "in"
    //        /countries/US  → code = "US"
    //        /countries/De  → code = "De"
    //   All three above will return the same country because
    //   CountryService.getCountry() uses equalsIgnoreCase()
    //
    // @PathVariable
    //   Binds the {code} placeholder from the URL to the method parameter.
    //   Spring extracts the value and passes it into getCountry(code).
    // -------------------------------------------------------
    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) {
        LOGGER.info("Start");
        LOGGER.debug("Received request for country code={}", code);

        // Delegate to service — service does the lookup in country.xml
        Country country = countryService.getCountry(code);

        LOGGER.debug("country={}", country);
        LOGGER.info("End");

        return country;
    }
}
