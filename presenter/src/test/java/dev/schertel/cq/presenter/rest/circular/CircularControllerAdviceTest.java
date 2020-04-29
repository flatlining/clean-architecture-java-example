package dev.schertel.cq.presenter.rest.circular;

import dev.schertel.cq.core.domain.DomainException;
import dev.schertel.cq.core.domain.NotFoundException;
import dev.schertel.cq.presenter.rest.entity.ApiResponse;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RandomBeansExtension.class)
class CircularControllerAdviceTest {

    CircularControllerAdvice cut;

    @BeforeEach
    void setUp() {
        this.cut = new CircularControllerAdvice();
    }

    @Test
    void customNotFoundException(@Random String value) {
        // Given
        NotFoundException exception = new NotFoundException(value);

        // When
        ResponseEntity<ApiResponse> actual = cut.customNotFoundException(exception);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode(), "status");
        assertAll("body",
                () -> assertNotNull(actual.getBody().getTimestamp()),
                () -> assertEquals(HttpStatus.NOT_FOUND.value(), actual.getBody().getStatus()),
                () -> assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), actual.getBody().getReason()),
                () -> assertTrue(actual.getBody().getMessage().contains(value))
        );
    }

    @Test
    void customDomainException(@Random String value) {
        // Given
        DomainException exception = new NotFoundException(value);

        // When
        ResponseEntity<ApiResponse> actual = cut.customDomainException(exception);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actual.getStatusCode(), "status");
        assertAll("body",
                () -> assertNotNull(actual.getBody().getTimestamp()),
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getBody().getStatus()),
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), actual.getBody().getReason()),
                () -> assertTrue(actual.getBody().getMessage().contains(value))
        );
    }
}
