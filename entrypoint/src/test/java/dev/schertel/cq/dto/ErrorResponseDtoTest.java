package dev.schertel.cq.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseDtoTest {

    @Test
    void nullObject() {
        ErrorResponseDto dto = ErrorResponseDto.builder().build();

        assertAll(
                () -> assertNull(dto.getTimestamp()),
                () -> assertNull(dto.getStatus()),
                () -> assertNull(dto.getError()),
                () -> assertNull(dto.getMessage())
        );
    }

    @Test
    void getTimestamp() {
        LocalDateTime expected = LocalDateTime.now();

        ErrorResponseDto dto = ErrorResponseDto.builder().withTimestamp(expected).build();

        assertAll(
                () -> assertEquals(expected, dto.getTimestamp()),
                () -> assertNull(dto.getStatus()),
                () -> assertNull(dto.getError()),
                () -> assertNull(dto.getMessage())
        );
    }

    @Test
    void getStatus() {
        Integer expected = 1;

        ErrorResponseDto dto = ErrorResponseDto.builder().withStatus(expected).build();

        assertAll(
                () -> assertNull(dto.getTimestamp()),
                () -> assertEquals(expected, dto.getStatus()),
                () -> assertNull(dto.getError()),
                () -> assertNull(dto.getMessage())
        );
    }

    @Test
    void getError() {
        String expected = "error";

        ErrorResponseDto dto = ErrorResponseDto.builder().withError(expected).build();

        assertAll(
                () -> assertNull(dto.getTimestamp()),
                () -> assertNull(dto.getStatus()),
                () -> assertEquals(expected, dto.getError()),
                () -> assertNull(dto.getMessage())
        );
    }

    @Test
    void getMessage() {
        String expected = "message";

        ErrorResponseDto dto = ErrorResponseDto.builder().withMessage(expected).build();

        assertAll(
                () -> assertNull(dto.getTimestamp()),
                () -> assertNull(dto.getStatus()),
                () -> assertNull(dto.getError()),
                () -> assertEquals(expected, dto.getMessage())
        );
    }

    @Test
    void fullObject() {
        LocalDateTime timestamp = LocalDateTime.now();
        Integer status = 1;
        String error = "error";
        String message = "message";

        ErrorResponseDto dto = ErrorResponseDto.builder()
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