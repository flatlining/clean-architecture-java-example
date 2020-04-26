package dev.schertel.cq.presenter.rest.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(setterPrefix = "with")
@EqualsAndHashCode
@ToString
@JsonDeserialize(builder = ApiResponse.ApiResponseBuilder.class)
public class ApiResponse {
    private final LocalDateTime timestamp;
    private final Integer status;
    private final String reason;
    private final String message;
}
