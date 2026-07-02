package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @SpringBootApplication is a convenience annotation that combines:
 *
 *   @Configuration
 *     Marks this class as a source of Spring bean definitions.
 *     Equivalent to writing beans in applicationContext.xml.
 *
 *   @EnableAutoConfiguration
 *     Tells Spring Boot to automatically configure the application
 *     based on the JARs present on the classpath.
 *     Example: spring-boot-starter-web on classpath → auto-configures
 *     DispatcherServlet, embedded Tomcat, Jackson, etc.
 *
 *   @ComponentScan
 *     Scans the current package (com.cognizant.springlearn) and all
 *     sub-packages for @Component, @Service, @Repository, @Controller
 *     and registers them as Spring beans automatically.
 */
@SpringBootApplication
public class SpringLearnApplication {

    // SLF4J Logger — uses Logback underneath (included via spring-boot-starter-web)
    // The log output will show "SpringLearnApplication" as the logger name
    private static final Logger LOGGER =
            LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {

        /*
         * SpringApplication.run() does the following:
         *   1. Creates the Spring ApplicationContext
         *   2. Reads application.properties
         *   3. Performs auto-configuration based on classpath
         *   4. Starts the embedded Tomcat server on port 8080
         *   5. Scans for @Controller, @Service, @Repository beans
         *   6. Application is ready to handle HTTP requests
         */
        SpringApplication.run(SpringLearnApplication.class, args);

        // Step 9: Log message to verify main() was called
        LOGGER.info("Inside main — SpringLearnApplication started successfully");
    }
}
