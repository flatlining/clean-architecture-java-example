package dev.schertel.cq.data.repository;

import dev.schertel.cq.core.domain.Circular;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(RandomBeansExtension.class)
class CircularRepositoryImplTest {
    @Mock private Logger logger;

    private CircularRepositoryImpl cut;

    @BeforeEach
    void setUp() {
        this.cut = new CircularRepositoryImpl(logger);
    }

    @Test
    void create(@Random Circular expected) {
        assertEquals(expected, cut.create(expected));
    }
}