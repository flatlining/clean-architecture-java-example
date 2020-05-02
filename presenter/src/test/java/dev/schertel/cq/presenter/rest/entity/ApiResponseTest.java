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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomBeansExtension.class)
class ApiResponseTest {
    private final Class<ApiResponse> CLAZZ = ApiResponse.class;

    private ApiResponse.Builder builder;

    @BeforeEach
    void setUp() {
        this.builder = ApiResponse.builder();
    }

    @Test
    void getTimestamp(@Random ZonedDateTime timestamp) {
        // Given
        builder.withTimestamp(timestamp);

        // When
        ApiResponse actual = builder.build();

        // Then
        assertThat(actual).isNotNull().satisfies(apiResponse -> {
            assertThat(apiResponse.getTimestamp()).isEqualTo(timestamp);
            assertThat(apiResponse.getStatus()).isNull();
            assertThat(apiResponse.getReason()).isNull();
            assertThat(apiResponse.getMessage()).isNull();
        });
    }

    @Test
    void getStatus(@Random HttpStatus httpStatus) {
        // Given
        Integer status = httpStatus.value();
        builder.withStatus(status);

        // When
        ApiResponse actual = builder.build();

        // Then
        assertThat(actual).isNotNull().satisfies(apiResponse -> {
            assertThat(apiResponse.getTimestamp()).isNull();
            assertThat(apiResponse.getStatus()).isEqualTo(status);
            assertThat(apiResponse.getReason()).isNull();
            assertThat(apiResponse.getMessage()).isNull();
        });
    }

    @Test
    void getReason(@Random HttpStatus httpStatus) {
        // Given
        String reason = httpStatus.getReasonPhrase();
        builder.withReason(reason);

        // When
        ApiResponse actual = builder.build();

        // Then
        assertThat(actual).isNotNull().satisfies(apiResponse -> {
            assertThat(apiResponse.getTimestamp()).isNull();
            assertThat(apiResponse.getStatus()).isNull();
            assertThat(apiResponse.getReason()).isEqualTo(reason);
            assertThat(apiResponse.getMessage()).isNull();
        });
    }

    @Test
    void getMessage(@Random String message) {
        // Given
        builder.withMessage(message);

        // When
        ApiResponse actual = builder.build();

        // Then
        assertThat(actual).isNotNull().satisfies(apiResponse -> {
            assertThat(apiResponse.getTimestamp()).isNull();
            assertThat(apiResponse.getStatus()).isNull();
            assertThat(apiResponse.getReason()).isNull();
            assertThat(apiResponse.getMessage()).isEqualTo(message);
        });
    }

    @Nested
    class Builder {
        @Test
        void nullObject() {
            // Given

            // When
            ApiResponse actual = builder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(apiResponse -> {
                assertThat(apiResponse.getTimestamp()).isNull();
                assertThat(apiResponse.getStatus()).isNull();
                assertThat(apiResponse.getReason()).isNull();
                assertThat(apiResponse.getMessage()).isNull();
            });
        }

        @Test
        void fullObject(@Random ZonedDateTime timestamp, @Random HttpStatus httpStatus, @Random String message) {
            // Given
            builder
                    .withTimestamp(timestamp)
                    .withStatus(httpStatus.value())
                    .withReason(httpStatus.getReasonPhrase())
                    .withMessage(message);

            // When
            ApiResponse actual = builder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(apiResponse -> {
                assertThat(apiResponse.getTimestamp()).isEqualTo(timestamp);
                assertThat(apiResponse.getStatus()).isEqualTo(httpStatus.value());
                assertThat(apiResponse.getReason()).isEqualTo(httpStatus.getReasonPhrase());
                assertThat(apiResponse.getMessage()).isEqualTo(message);
            });
        }
    }

    @Nested
    class JSON {
        @Test
        void serializationDeserialization(@Random ZonedDateTime now, @Random HttpStatus httpStatus, @Random String message) throws JsonProcessingException {
            // Background
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

            // Given
            String timestamp = FMT.format(now);
            Integer status = httpStatus.value();
            String reason = httpStatus.getReasonPhrase();
            String json = String.format("{\n" +
                    "  \"timestamp\":\"%s\",\n" +
                    "  \"status\":%d,\n" +
                    "  \"reason\":\"%s\",\n" +
                    "  \"message\":\"%s\"\n" +
                    "}", timestamp, status, reason, message);

            // When
            ApiResponse entityFromBuilder = builder
                    .withTimestamp(now)
                    .withStatus(status)
                    .withReason(reason)
                    .withMessage(message).build();
            String jsonFromBuilder = mapper.writeValueAsString(entityFromBuilder);

            // Then
            ApiResponse entityFromJson = mapper.readValue(json, CLAZZ);
            String jsonFromJSon = mapper.writeValueAsString(entityFromJson);
            
            assertThat(jsonFromBuilder).isEqualTo(jsonFromJSon);
            assertThat(mapper.readValue(jsonFromBuilder, CLAZZ))
                    .isEqualTo(mapper.readValue(jsonFromJSon, CLAZZ));
            assertThat(mapper.readValue(jsonFromBuilder, CLAZZ).toString())
                    .isEqualTo(mapper.readValue(jsonFromJSon, CLAZZ).toString());
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