package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.exception.CircularQueueNotFoundException;
import dev.schertel.cq.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CircularQueueControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CircularQueueNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> customHandleNotFound(CircularQueueNotFoundException ex, WebRequest request) {
        LocalDateTime timestamp = LocalDateTime.now();
        Integer status = HttpStatus.NOT_FOUND.value();
        String error = HttpStatus.NOT_FOUND.getReasonPhrase();
        String message = String.format("Unable to find Circular Queue with id %s, make sure to use a valid id.", ex.getId());
        ErrorResponseDto errorResponse = new ErrorResponseDto(timestamp, status, error, message);
        return new ResponseEntity<ErrorResponseDto>(errorResponse, HttpStatus.NOT_FOUND);

    }
}
