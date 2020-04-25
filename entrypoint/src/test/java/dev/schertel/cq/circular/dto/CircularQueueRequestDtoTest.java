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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RandomBeansExtension.class)
class CircularQueueRequestDtoTest {
    private CircularQueueRequestDto.Builder builder = CircularQueueRequestDto.builder();

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

    @Nested
    class Builder {
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
    }

    @Nested
    class JSON {
        @Test
        void serializationDeserialization(@Random String name, @Random String description) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();

            String json = String.format("{\n" +
                    "  \"name\":\"%s\",\n" +
                    "  \"description\":\"%s\"\n" +
                    "}", name, description);
            CircularQueueRequestDto entityFromJson = mapper.readValue(json, CircularQueueRequestDto.class);
            String jsonFromJSon = mapper.writeValueAsString(entityFromJson);

            CircularQueueRequestDto entityFromBuilder = builder.withName(name).withDescription(description).build();
            String jsonFromBuilder = mapper.writeValueAsString(entityFromBuilder);

            assertAll(
                    () -> assertEquals(jsonFromJSon, jsonFromBuilder),
                    () -> assertEquals(entityFromJson, entityFromBuilder),
                    () -> assertEquals(entityFromJson.toString(), entityFromBuilder.toString())
            );
        }
    }

    @Nested
    class Override {
        @Test
        void testToString() {
            ToStringVerifier.forClass(CircularQueueRequestDto.class)
                    .withClassName(NameStyle.SIMPLE_NAME)
                    .withPreset(Presets.INTELLI_J)
                    .verify();
        }

        @Test
        void testEquals() {
            EqualsVerifier.forClass(CircularQueueRequestDto.class).suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
        }
    }
}