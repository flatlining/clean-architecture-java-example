package dev.schertel.cq.presenter.rest.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(setterPrefix = "with")
@EqualsAndHashCode
@ToString
public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final Integer status;
    private final String error;
    private final String message;
}
