package dev.schertel.cq.presenter.rest.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.presenter.rest.entity.CircularResponse;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(RandomBeansExtension.class)
class CircularMapperTest {
    CircularMapper cut;

    @BeforeEach
    void setUp() {
        this.cut = new CircularMapper();
    }

    @Test
    void convertEntityToResponse(@Random Circular entity) {
        CircularResponse actual = cut.convertEntityToResponse(entity);

        assertAll(
                () -> assertEquals(entity.getId().getId(), actual.getId(), "id"),
                () -> assertEquals(entity.getName(), actual.getName(), "name"),
                () -> assertEquals(entity.getDescription(), actual.getDescription(), "description")
        );
    }
}
