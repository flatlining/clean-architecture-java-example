package dev.schertel.cq.presenter.rest.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RandomBeansExtension.class)
class ApiResponseTest {
    private final Class<ApiResponse> CLAZZ = ApiResponse.class;

    private ApiResponse.Builder builder;
    private ApiResponse cut;

    @BeforeEach
    void setUp() {
        this.builder = ApiResponse.builder();
        this.cut = null;
    }

    @Test
    void getTimestamp(@Random ZonedDateTime timestamp) {
        cut = builder
                .withTimestamp(timestamp)
                .build();

        assertAll(
                () -> assertEquals(timestamp, cut.getTimestamp()),
                () -> assertNull(cut.getStatus()),
                () -> assertNull(cut.getReason()),
                () -> assertNull(cut.getMessage())
        );
    }

    @Test
    void getStatus(@Random HttpStatus httpStatus) {
        Integer status = httpStatus.value();

        cut = builder
                .withStatus(status)
                .build();

        assertAll(
                () -> assertNull(cut.getTimestamp()),
                () -> assertEquals(status, cut.getStatus()),
                () -> assertNull(cut.getReason()),
                () -> assertNull(cut.getMessage())
        );
    }

    @Test
    void getReason(@Random HttpStatus httpStatus) {
        String reason = httpStatus.getReasonPhrase();

        cut = builder
                .withReason(reason)
                .build();

        assertAll(
                () -> assertNull(cut.getTimestamp()),
                () -> assertNull(cut.getStatus()),
                () -> assertEquals(reason, cut.getReason()),
                () -> assertNull(cut.getMessage())
        );
    }

    @Test
    void getMessage(@Random String message) {
        cut = builder
                .withMessage(message)
                .build();

        assertAll(
                () -> assertNull(cut.getTimestamp()),
                () -> assertNull(cut.getStatus()),
                () -> assertNull(cut.getReason()),
                () -> assertEquals(message, cut.getMessage())
        );
    }

    @Nested
    class Builder {
        @Test
        void nullObject() {
            cut = builder.build();

            assertAll(
                    () -> assertNull(cut.getTimestamp()),
                    () -> assertNull(cut.getStatus()),
                    () -> assertNull(cut.getReason()),
                    () -> assertNull(cut.getMessage())
            );
        }

        @Test
        void fullObject(@Random ZonedDateTime timestamp, @Random HttpStatus httpStatus, @Random String message) {
            cut = builder
                    .withTimestamp(timestamp)
                    .withStatus(httpStatus.value())
                    .withReason(httpStatus.getReasonPhrase())
                    .withMessage(message)
                    .build();

            assertAll(
                    () -> assertEquals(timestamp, cut.getTimestamp()),
                    () -> assertEquals(httpStatus.value(), cut.getStatus()),
                    () -> assertEquals(httpStatus.getReasonPhrase(), cut.getReason()),
                    () -> assertEquals(message, cut.getMessage())
            );
        }
    }

    @Nested
    class JSON {
        @Test
        void serializationDeserialization(@Random ZonedDateTime now, @Random HttpStatus httpStatus, @Random String message) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

            DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            String timestamp = FMT.format(now);

            Integer status = httpStatus.value();
            String reason = httpStatus.getReasonPhrase();

            String json = String.format("{\n" +
                    "  \"timestamp\":\"%s\",\n" +
                    "  \"status\":%d,\n" +
                    "  \"reason\":\"%s\",\n" +
                    "  \"message\":\"%s\"\n" +
                    "}", timestamp, status, reason, message);
            ApiResponse entityFromJson = mapper.readValue(json, CLAZZ);
            String jsonFromJSon = mapper.writeValueAsString(entityFromJson);

            ApiResponse entityFromBuilder = builder.withTimestamp(now).withStatus(status).withReason(reason).withMessage(message).build();
            String jsonFromBuilder = mapper.writeValueAsString(entityFromBuilder);

            assertEquals(jsonFromJSon, jsonFromBuilder);
            assertEquals(mapper.readValue(jsonFromJSon, CLAZZ), mapper.readValue(jsonFromBuilder, CLAZZ));
            assertEquals(mapper.readValue(jsonFromJSon, CLAZZ).toString(), mapper.readValue(jsonFromBuilder, CLAZZ).toString());
        }
    }

    @Nested
    class Override {
        @Test
        void testToString() {
            ToStringVerifier.forClass(CLAZZ)
                    .withClassName(NameStyle.SIMPLE_NAME)
                    .verify();
        }

        @Test
        void testEquals() {
            EqualsVerifier.forClass(CLAZZ)
                    .verify();
        }
    }
}