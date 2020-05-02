package dev.schertel.cq.data.repository;

import dev.schertel.cq.core.domain.Circular;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.slf4j.Logger;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(RandomBeansExtension.class)
class CircularRepositoryImplTest {
    @Mock
    private Logger logger;

    private CircularRepositoryImpl cut;

    @BeforeEach
    void setUp() {
        this.cut = new CircularRepositoryImpl(logger);
    }

    @Test
    void create(@Random Circular expected) {
        assertEquals(expected, cut.create(expected));
    }

    @Test
    void realAllEmpty() {
        assertTrue(cut.readAll().isEmpty());
    }

    @Test
    void realAllNonEmpty(@Random(size = 5, type = Circular.class) List<Circular> expected) {
        expected.forEach(circular -> {
            cut.create(circular);
        });

        List<Circular> actual = cut.readAll();

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}