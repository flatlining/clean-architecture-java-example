package dev.schertel.cq.circular.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import com.jparams.verifier.tostring.preset.Presets;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RandomBeansExtension.class)
class CircularQueueResponseDtoTest {
    private CircularQueueResponseDto.Builder builder;

    @BeforeEach
    void setUp() {
        this.builder = CircularQueueResponseDto.builder();
    }

    @Test
    void json(@Random String id, @Random String name, @Random String description) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String json = String.format("{\n" +
                "  \"id\":\"%s\",\n" +
                "  \"name\":\"%s\",\n" +
                "  \"description\":\"%s\"\n" +
                "}", id, name, description);
        CircularQueueResponseDto entityFromJson = mapper.readValue(json, CircularQueueResponseDto.class);
        String jsonFromJSon = mapper.writeValueAsString(entityFromJson);

        CircularQueueResponseDto entityFromBuilder = builder.withId(id).withName(name).withDescription(description).build();
        String jsonFromBuilder = mapper.writeValueAsString(entityFromBuilder);

        assertAll(
                () -> assertEquals(jsonFromJSon, jsonFromBuilder),
                () -> assertEquals(entityFromJson, entityFromBuilder),
                () -> assertEquals(entityFromJson.toString(), entityFromBuilder.toString())
        );
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
    void getId(@Random String expected) {
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
    void getName(@Random String expected) {
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
    void getDescription(@Random String expected) {
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
    void fullObject(@Random String id, @Random String name, @Random String description) {
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

    @Test
    void testEquals() {
        EqualsVerifier.forClass(CircularQueueResponseDto.class).suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
    }
}