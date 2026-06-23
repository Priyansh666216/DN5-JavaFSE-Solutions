package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {

    // Logger is created once per class — uses the class name as the logger name
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {

        System.out.println("======= SLF4J Logging Demo =======\n");

        // -------------------------------------------------------
        // Log Levels (lowest → highest severity)
        // TRACE < DEBUG < INFO < WARN < ERROR
        // -------------------------------------------------------

        // TRACE: finest detail — method entry/exit, loop values
        logger.trace("TRACE: entering main() method");

        // DEBUG: developer info — variable values, flow steps
        logger.debug("DEBUG: application started with {} argument(s)", args.length);

        // INFO: normal operations — startup messages, milestone events
        logger.info("INFO: application is running successfully");

        // WARN: something unexpected but not breaking
        logger.warn("WARN: configuration file not found, using defaults");

        // ERROR: something failed — exceptions, critical failures
        logger.error("ERROR: failed to connect to database");

        System.out.println();

        // -------------------------------------------------------
        // Parameterized logging (avoids string concatenation cost)
        // Use {} as placeholder instead of "Hello " + name
        // -------------------------------------------------------
        String username = "Priyanshu";
        int userId      = 101;

        logger.info("User '{}' logged in with ID {}", username, userId);
        logger.warn("User '{}' attempted an unauthorized action", username);

        System.out.println();

        // -------------------------------------------------------
        // Logging exceptions
        // Pass the exception as the LAST argument — Logback prints
        // the full stack trace automatically
        // -------------------------------------------------------
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("ERROR: division failed due to invalid input", e);
        }

        try {
            String text = null;
            int length = text.length();
        } catch (NullPointerException e) {
            logger.warn("WARN: null value encountered while reading text", e);
        }

        System.out.println("\n======= Demo Complete =======");
    }
}
