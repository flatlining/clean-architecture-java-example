package dev.schertel.cq.config;

import dev.schertel.cq.core.usecase.circular.CircularRepository;
import dev.schertel.cq.data.database.circular.CircularEntityRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = DataConfig.class)
class DataConfigTest {
    @MockBean
    Logger logger;
    @MockBean
    CircularEntityRepository circularEntityRepository;

    @Autowired
    CircularRepository circularRepository;

    @Test
    void circularRepository() {
        assertNotNull(circularRepository);
    }
}
