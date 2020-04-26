package dev.schertel.cq.presenter.rest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@Builder(builderClassName = "Builder", setterPrefix = "with")
@EqualsAndHashCode
@ToString
@JsonDeserialize(builder = ApiResponse.Builder.class)
public class ApiResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private final ZonedDateTime timestamp;
    private final Integer status;
    private final String reason;
    private final String message;

    @JsonPOJOBuilder
    public static class Builder {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
        private ZonedDateTime timestamp;
    }
}
