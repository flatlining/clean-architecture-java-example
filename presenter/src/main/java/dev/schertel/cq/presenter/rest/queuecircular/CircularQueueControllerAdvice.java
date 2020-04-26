package dev.schertel.cq.presenter.rest.queuecircular;

import dev.schertel.cq.core.domain.NotFoundException;
import dev.schertel.cq.presenter.rest.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CircularQueueControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> customHandleNotFound(NotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .withTimestamp(LocalDateTime.now())
                .withStatus(HttpStatus.NOT_FOUND.value())
                .withError(HttpStatus.NOT_FOUND.getReasonPhrase())
                .withMessage(ex.getMessage())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
