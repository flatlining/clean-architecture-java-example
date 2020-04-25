package dev.schertel.cq.dto;

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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RandomBeansExtension.class)
class ErrorResponseDtoTest {
    private ErrorResponseDto.Builder builder = ErrorResponseDto.builder();

    @Test
    void getTimestamp(@Random LocalDateTime expected) {
        ErrorResponseDto dto = builder
                .withTimestamp(expected)
                .build();

        assertAll(
                () -> assertEquals(expected, dto.getTimestamp()),
                () -> assertNull(dto.getStatus()),
                () -> assertNull(dto.getError()),
                () -> assertNull(dto.getMessage())
        );
    }

    @Test
    void getStatus(@Random Integer expected) {
        ErrorResponseDto dto = builder
                .withStatus(expected)
                .build();

        assertAll(
                () -> assertNull(dto.getTimestamp()),
                () -> assertEquals(expected, dto.getStatus()),
                () -> assertNull(dto.getError()),
                () -> assertNull(dto.getMessage())
        );
    }

    @Test
    void getError(@Random String expected) {
        ErrorResponseDto dto = builder
                .withError(expected)
                .build();

        assertAll(
                () -> assertNull(dto.getTimestamp()),
                () -> assertNull(dto.getStatus()),
                () -> assertEquals(expected, dto.getError()),
                () -> assertNull(dto.getMessage())
        );
    }

    @Test
    void getMessage(@Random String expected) {
        ErrorResponseDto dto = builder
                .withMessage(expected)
                .build();

        assertAll(
                () -> assertNull(dto.getTimestamp()),
                () -> assertNull(dto.getStatus()),
                () -> assertNull(dto.getError()),
                () -> assertEquals(expected, dto.getMessage())
        );
    }

    @Nested
    class Builder {
        @Test
        void nullObject() {
            ErrorResponseDto dto = builder
                    .build();

            assertAll(
                    () -> assertNull(dto.getTimestamp()),
                    () -> assertNull(dto.getStatus()),
                    () -> assertNull(dto.getError()),
                    () -> assertNull(dto.getMessage())
            );
        }

        @Test
        void fullObject(@Random LocalDateTime timestamp, @Random Integer status, @Random String error, @Random String message) {
            ErrorResponseDto dto = builder
                    .withTimestamp(timestamp)
                    .withStatus(status)
                    .withError(error)
                    .withMessage(message)
                    .build();

            assertAll(
                    () -> assertEquals(timestamp, dto.getTimestamp()),
                    () -> assertEquals(status, dto.getStatus()),
                    () -> assertEquals(error, dto.getError()),
                    () -> assertEquals(message, dto.getMessage())
            );
        }
    }

    @Nested
    class JSON {
        @Test
        void serializationDeserialization(@Random LocalDateTime timestamp, @Random Integer status, @Random String error, @Random String message) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();

            String json = String.format("{\n" +
                    "  \"timestamp\":\"%s\",\n" +
                    "  \"status\":%d,\n" +
                    "  \"error\":\"%s\",\n" +
                    "  \"message\":\"%s\"\n" +
                    "}", timestamp, status, error, message);
            ErrorResponseDto entityFromJson = mapper.readValue(json, ErrorResponseDto.class);
            String jsonFromJSon = mapper.writeValueAsString(entityFromJson);

            ErrorResponseDto entityFromBuilder = builder.withTimestamp(timestamp).withStatus(status).withError(error).withMessage(message).build();
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
            ToStringVerifier.forClass(ErrorResponseDto.class)
                    .withClassName(NameStyle.SIMPLE_NAME)
                    .withPreset(Presets.INTELLI_J)
                    .verify();
        }

        @Test
        void testEquals() {
            EqualsVerifier.forClass(ErrorResponseDto.class).suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
        }
    }
}