package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Country {

    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    private String code;
    private String name;

    // -------------------------------------------------------
    // No-arg constructor
    // Spring calls this first before injecting property values
    // -------------------------------------------------------
    public Country() {
        LOGGER.debug("Inside Country Constructor.");
    }

    // -------------------------------------------------------
    // Getters
    // -------------------------------------------------------

    public String getCode() {
        LOGGER.debug("Inside getCode. code={}", code);
        return code;
    }

    public String getName() {
        LOGGER.debug("Inside getName. name={}", name);
        return name;
    }

    // -------------------------------------------------------
    // Setters
    // Spring calls these after the constructor to inject
    // the values defined in <property> tags in country.xml
    // -------------------------------------------------------

    public void setCode(String code) {
        LOGGER.debug("Inside setCode. code={}", code);
        this.code = code;
    }

    public void setName(String name) {
        LOGGER.debug("Inside setName. name={}", name);
        this.name = name;
    }

    // -------------------------------------------------------
    // toString
    // Used when LOGGER.debug("country={}", country) is called
    // -------------------------------------------------------
    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
