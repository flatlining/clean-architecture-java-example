package dev.schertel.cq.presenter.rest.circular;

import dev.schertel.cq.core.domain.DomainException;
import dev.schertel.cq.core.domain.NotFoundException;
import dev.schertel.cq.presenter.rest.entity.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CircularControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> customNotFoundException(NotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiResponse apiResponse = ApiResponse.builder()
                .withTimestamp(ZonedDateTime.now(ZoneOffset.UTC))
                .withStatus(httpStatus.value())
                .withReason(httpStatus.getReasonPhrase())
                .withMessage(ex.getMessage())
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, httpStatus);
    }

    @ExceptionHandler(value = {DomainException.class})
    public ResponseEntity<ApiResponse> customDomainException(DomainException ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiResponse apiResponse = ApiResponse.builder()
                .withTimestamp(ZonedDateTime.now(ZoneOffset.UTC))
                .withStatus(httpStatus.value())
                .withReason(httpStatus.getReasonPhrase())
                .withMessage(ex.getMessage())
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, httpStatus);
    }
}
