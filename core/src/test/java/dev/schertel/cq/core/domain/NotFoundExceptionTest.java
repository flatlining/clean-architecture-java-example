package dev.schertel.cq.core.domain;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(RandomBeansExtension.class)
class NotFoundExceptionTest {
    private NotFoundException cut;

    @BeforeEach
    void setUp() {
        this.cut = null;
    }

    @Test
    void getMessage(@Random String message) {
        cut = new NotFoundException(message);

        assertEquals(message, cut.getMessage());
    }

    @Test
    void getMessageFormat() {
        cut = new NotFoundException("This is a message format with %s parameter.", Integer.valueOf(1));

        assertEquals("This is a message format with 1 parameter.", cut.getMessage());
    }

    @Test
    void of(@Random String value) {
        cut = NotFoundException.of(value);

        assertEquals(value, cut.getMessage());
    }
}