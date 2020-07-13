package dev.schertel.cq.config;

import dev.schertel.cq.core.usecase.circular.*;
import dev.schertel.cq.core.usecase.identity.GenerateRandomIdentityUseCase;
import dev.schertel.cq.data.database.circular.CircularEntityRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {DataConfig.class, CoreConfig.class})
class CoreConfigTest {
    @MockBean
    Logger logger;
    @MockBean
    CircularEntityRepository circularEntityRepository;

    @Autowired
    GenerateRandomIdentityUseCase generateRandomIdentityUseCase;
    @Autowired
    CreateCircularUseCase createCircularUseCase;
    @Autowired
    ReadAllCircularUseCase readAllCircularUseCase;
    @Autowired
    ReadCircularUseCase readCircularUseCase;
    @Autowired
    DeleteAllCircularUseCase deleteAllCircularUseCase;
    @Autowired
    DeleteCircularUseCase deleteCircularUseCase;

    @Test
    void generateRandomIdentityUseCase() {
        assertNotNull(generateRandomIdentityUseCase);
    }

    @Test
    void createCircularUseCase() {
        assertNotNull(createCircularUseCase);
    }

    @Test
    void readAllCircularUseCase() {
        assertNotNull(readAllCircularUseCase);
    }

    @Test
    void readCircularUseCase() {
        assertNotNull(readAllCircularUseCase);
    }

    @Test
    void deleteAllCircularUseCase() {
        assertNotNull(deleteAllCircularUseCase);
    }

    @Test
    void deleteCircularUseCase() {
        assertNotNull(deleteCircularUseCase);
    }
}
