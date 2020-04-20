package dev.schertel.cq.circular.dto;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import com.jparams.verifier.tostring.preset.Presets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularQueueRequestDtoTest {
    private CircularQueueRequestDto.Builder builder;

    @BeforeEach
    void setUp() {
        builder = CircularQueueRequestDto.builder();
    }

    @Test
    void nullObject() {
        CircularQueueRequestDto dto = builder.build();

        assertAll(
                () -> assertNull(dto.getName()),
                () -> assertNull(dto.getDescription())
        );
    }

    @Test
    void getName() {
        String expected = "name";

        CircularQueueRequestDto dto = builder
                .withName(expected)
                .build();

        assertAll(
                () -> assertEquals(expected, dto.getName()),
                () -> assertNull(dto.getDescription())
        );
    }

    @Test
    void getDescription() {
        String expected = "description";

        CircularQueueRequestDto dto = builder
                .withDescription(expected)
                .build();

        assertAll(
                () -> assertNull(dto.getName()),
                () -> assertEquals(expected, dto.getDescription())
        );
    }

    @Test
    void fullObject() {
        String name = "name";
        String description = "description";

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