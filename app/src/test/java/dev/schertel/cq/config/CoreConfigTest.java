package dev.schertel.cq.config;

import dev.schertel.cq.core.usecase.circular.*;
import dev.schertel.cq.core.usecase.identity.GenerateRandomIdentityUseCase;
import dev.schertel.cq.data.database.circular.CircularEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {DataConfig.class, CoreConfig.class})
class CoreConfigTest {

    @MockBean
    CircularEntityRepository circularEntityRepository;

    @Autowired
    private GenerateRandomIdentityUseCase generateRandomIdentityUseCase;

    @Autowired
    private CreateCircularUseCase createCircularUseCase;

    @Autowired
    private ReadAllCircularUseCase readAllCircularUseCase;

    @Autowired
    private ReadCircularUseCase readCircularUseCase;

    @Autowired
    private DeleteAllCircularUseCase deleteAllCircularUseCase;

    @Autowired
    private DeleteCircularUseCase deleteCircularUseCase;

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
