package dev.schertel.cq.circular.dto;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import com.jparams.verifier.tostring.preset.Presets;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RandomBeansExtension.class)
class CircularQueueRequestDtoTest {
    private CircularQueueRequestDto.Builder builder;

    @BeforeEach
    void setUp() {
        builder = CircularQueueRequestDto.builder();
    }

    @Test
    void nullObject() {
        CircularQueueRequestDto dto = builder
                .build();

        assertAll(
                () -> assertNull(dto.getName()),
                () -> assertNull(dto.getDescription())
        );
    }

    @Test
    void getName(@Random String expected) {
        CircularQueueRequestDto dto = builder
                .withName(expected)
                .build();

        assertAll(
                () -> assertEquals(expected, dto.getName()),
                () -> assertNull(dto.getDescription())
        );
    }

    @Test
    void getDescription(@Random String expected) {
        CircularQueueRequestDto dto = builder
                .withDescription(expected)
                .build();

        assertAll(
                () -> assertNull(dto.getName()),
                () -> assertEquals(expected, dto.getDescription())
        );
    }

    @Test
    void fullObject(@Random String name, @Random String description) {
        CircularQueueRequestDto dto = builder
                .withName(name)
                .withDescription(description)
                .build();

        assertAll(
                () -> assertEquals(name, dto.getName()),
                () -> assertEquals(description, dto.getDescription())
        );
    }

    @Test
    void testToString() {
        ToStringVerifier.forClass(CircularQueueRequestDto.class)
                .withClassName(NameStyle.SIMPLE_NAME)
                .withPreset(Presets.INTELLI_J)
                .verify();
    }
}