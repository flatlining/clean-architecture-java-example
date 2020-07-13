package dev.schertel.cq.config;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = LoggerConfig.class)
class LoggerConfigTest {
    @Autowired
    private Logger logger;

    @Test
    void logger() {
        assertNotNull(logger);
    }
}
