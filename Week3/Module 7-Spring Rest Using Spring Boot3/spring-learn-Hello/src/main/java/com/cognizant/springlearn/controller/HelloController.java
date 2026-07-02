package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @RestController is a combination of two annotations:
 *
 *   @Controller
 *     Marks this class as a Spring MVC controller.
 *     Spring's DispatcherServlet routes incoming HTTP requests to methods here.
 *
 *   @ResponseBody
 *     Tells Spring to write the method's return value directly into the
 *     HTTP response body — no view (HTML page) is rendered.
 *     The string "Hello World!!" is sent back as plain text.
 */
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    /*
     * @GetMapping("/hello")
     *   Maps HTTP GET requests for the URL /hello to this method.
     *   Equivalent to: @RequestMapping(value="/hello", method=RequestMethod.GET)
     *
     * When a browser or Postman sends:
     *   GET http://localhost:8083/hello
     * Spring routes the request here and sends the returned string back as the response body.
     */
    @GetMapping("/hello")
    public String sayHello() {
        LOGGER.info("Start");

        String response = "Hello World!!";

        LOGGER.debug("response={}", response);
        LOGGER.info("End");

        return response;
    }
}
