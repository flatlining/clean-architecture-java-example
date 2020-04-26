package dev.schertel.cq.presenter.rest.circular;

import dev.schertel.cq.core.domain.NotFoundException;
import dev.schertel.cq.presenter.rest.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CircularControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> customHandleNotFound(NotFoundException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiResponse apiResponse = ApiResponse.builder()
                .withTimestamp(ZonedDateTime.now())
                .withStatus(httpStatus.value())
                .withReason(httpStatus.getReasonPhrase())
                .withMessage(ex.getMessage())
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, httpStatus);
    }
}
