package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    /*
     * getCountry(String code)
     *
     * Steps:
     *   1. Load country.xml from classpath
     *   2. Get the full countryList bean (all 4 countries)
     *   3. Use a lambda + stream to find a case-insensitive match on code
     *   4. Return the matched Country, or null if not found
     *
     * Case insensitivity: "in", "IN", "In" all match "IN"
     *   → achieved by calling equalsIgnoreCase() in the lambda filter
     */
    @SuppressWarnings("unchecked")
    public Country getCountry(String code) {
        LOGGER.info("Start");
        LOGGER.debug("Finding country for code={}", code);

        // Step 1 & 2: Load XML config and fetch the list of all countries
        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");

        List<Country> countryList =
                (List<Country>) context.getBean("countryList");

        LOGGER.debug("Total countries loaded={}", countryList.size());

        // Step 3: Lambda expression — filter by case-insensitive code match
        // stream()    → turns the list into a stream for functional operations
        // filter()    → keeps only countries whose code matches (ignoring case)
        // findFirst() → returns the first match as an Optional<Country>
        // orElse(null)→ returns null if no match found
        Country result = countryList.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        LOGGER.debug("result={}", result);
        LOGGER.info("End");

        return result;
    }
}
