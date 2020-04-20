package dev.schertel.cq.circular.dto;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import com.jparams.verifier.tostring.preset.Presets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularQueueResponseDtoTest {
    private CircularQueueResponseDto.Builder builder;

    @BeforeEach
    void setUp() {
        this.builder = CircularQueueResponseDto.builder();
    }

    @Test
    void nullObject() {
        CircularQueueResponseDto dto = builder
                .build();

        assertAll(
                () -> assertNull(dto.getId()),
                () -> assertNull(dto.getName()),
                () -> assertNull(dto.getDescription())
        );
    }

    @Test
    void getId() {
        String expected = "id";

        CircularQueueResponseDto dto = builder
                .withId(expected)
                .build();

        assertAll(
                () -> assertEquals(expected, dto.getId()),
                () -> assertNull(dto.getName()),
                () -> assertNull(dto.getDescription())
        );
    }

    @Test
    void getName() {
        String expected = "name";

        CircularQueueResponseDto dto = builder
                .withName(expected)
                .build();

        assertAll(
                () -> assertNull(dto.getId()),
                () -> assertEquals(expected, dto.getName()),
                () -> assertNull(dto.getDescription())
        );
    }

    @Test
    void getDescription() {
        String expected = "description";

        CircularQueueResponseDto dto = builder
                .withDescription(expected)
                .build();

        assertAll(
                () -> assertNull(dto.getId()),
                () -> assertNull(dto.getName()),
                () -> assertEquals(expected, dto.getDescription())
        );
    }

    @Test
    void fullObject() {
        String id = "id";
        String name = "name";
        String description = "description";

        CircularQueueResponseDto dto = builder
                .withId(id)
                .withName(name)
                .withDescription(description)
                .build();

        assertAll(
                () -> assertEquals(id, dto.getId()),
                () -> assertEquals(name, dto.getName()),
                () -> assertEquals(description, dto.getDescription())
        );
    }

    @Test
    void testToString() {
        ToStringVerifier.forClass(CircularQueueResponseDto.class)
                .withClassName(NameStyle.SIMPLE_NAME)
                .withPreset(Presets.INTELLI_J)
                .verify();
    }
}