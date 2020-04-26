package dev.schertel.cq.presenter.rest.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
@EqualsAndHashCode
@ToString
@JsonDeserialize(builder = CircularResponse.CircularResponseBuilder.class)
public class CircularResponse {
    private final String id;
    private final String name;
    private final String description;
}
