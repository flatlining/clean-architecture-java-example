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

import static org.assertj.core.api.Assertions.assertThat;

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
        HttpStatus expectedHttpStatus = HttpStatus.NOT_FOUND;
        assertThat(actual.getStatusCode()).isEqualTo(expectedHttpStatus);
        assertThat(actual.getBody()).isNotNull().satisfies(apiResponse -> {
            assertThat(apiResponse.getTimestamp()).isNotNull();
            assertThat(apiResponse.getStatus()).isEqualTo(expectedHttpStatus.value());
            assertThat(apiResponse.getReason()).isEqualTo(expectedHttpStatus.getReasonPhrase());
            assertThat(apiResponse.getMessage()).contains(value);
        });
    }

    @Test
    void customDomainException(@Random String value) {
        // Given
        DomainException exception = new NotFoundException(value);

        // When
        ResponseEntity<ApiResponse> actual = cut.customDomainException(exception);

        // Then
        HttpStatus expectedHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        assertThat(actual.getStatusCode()).isEqualTo(expectedHttpStatus);
        assertThat(actual.getBody()).isNotNull().satisfies(apiResponse -> {
            assertThat(apiResponse.getTimestamp()).isNotNull();
            assertThat(apiResponse.getStatus()).isEqualTo(expectedHttpStatus.value());
            assertThat(apiResponse.getReason()).isEqualTo(expectedHttpStatus.getReasonPhrase());
            assertThat(apiResponse.getMessage()).contains(value);
        });
    }
}
