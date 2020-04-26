package dev.schertel.cq.presenter.rest.dto;

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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RandomBeansExtension.class)
class ApiResponseTest {
    private ApiResponse.Builder builder;
    private ApiResponse cut;

    @BeforeEach
    void setUp() {
        this.builder = ApiResponse.builder();
        this.cut = null;
    }

    @Test
    void getTimestamp(@Random LocalDateTime timestamp) {
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
        void fullObject(@Random LocalDateTime timestamp, @Random HttpStatus httpStatus, @Random String message) {
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
    class Override {
        private final Class<?> CLAZZ = ApiResponse.class;

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