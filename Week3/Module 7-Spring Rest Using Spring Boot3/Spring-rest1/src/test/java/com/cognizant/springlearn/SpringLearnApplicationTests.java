package com.cognizant.springlearn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * @SpringBootTest
 * Loads the full Spring ApplicationContext for integration testing.
 * If the context fails to load (missing beans, config errors etc.)
 * this test will fail — acts as a basic smoke test.
 */
@SpringBootTest
class SpringLearnApplicationTests {

    @Test
    void contextLoads() {
        // Passes if the Spring context starts without errors
    }
}
