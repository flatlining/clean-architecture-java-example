package dev.schertel.cq.presenter.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(builderClassName = "Builder", setterPrefix = "with")
@EqualsAndHashCode
@ToString
@JsonDeserialize(builder = ApiResponse.Builder.class)
public class ApiResponse {
    private final LocalDateTime timestamp;
    private final Integer status;
    private final String reason;
    private final String message;

    @JsonPOJOBuilder
    public static class Builder {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime timestamp;
    }
}
