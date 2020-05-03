package dev.schertel.cq.presenter.rest.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.presenter.rest.entity.CircularResponse;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomBeansExtension.class)
class CircularMapperTest {
    CircularMapper cut;

    @BeforeEach
    void setUp() {
        this.cut = new CircularMapper();
    }

    @Test
    void convertEntityToResponse(@Random Circular entity) {
        // Given

        // When
        CircularResponse actual = cut.convertEntityToResponse(entity);

        // Then
        assertThat(actual).isNotNull().satisfies(circularResponse -> {
            assertThat(circularResponse.getId()).isEqualTo(entity.getId().getId());
            assertThat(circularResponse.getName()).isEqualTo(entity.getName());
            assertThat(circularResponse.getDescription()).isEqualTo(entity.getDescription());
        });
    }
}
