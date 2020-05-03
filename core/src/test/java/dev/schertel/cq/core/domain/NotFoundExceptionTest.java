package dev.schertel.cq.core.domain;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomBeansExtension.class)
class NotFoundExceptionTest {

    @Test
    void getMessage(@Random String message) {
        // Given

        // When
        NotFoundException actual = new NotFoundException(message);

        // Then
        assertThat(actual.getMessage()).isEqualTo(message);
    }

    @Test
    void getMessageFormat() {
        // Given
        String message = "This is a message format with %s parameter.";
        Integer parameter = 1;

        // When
        NotFoundException actual = new NotFoundException(message, parameter);

        // Then
        assertThat(actual.getMessage()).isEqualTo("This is a message format with 1 parameter.");
    }

    @Test
    void of(@Random String message) {
        // Given

        // When
        NotFoundException actual = NotFoundException.of(message);

        // Then
        assertThat(actual.getMessage()).isEqualTo(message);
    }
}
