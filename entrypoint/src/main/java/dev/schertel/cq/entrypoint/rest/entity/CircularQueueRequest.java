package dev.schertel.cq.entrypoint.rest.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
@EqualsAndHashCode
@ToString
public class CircularQueueRequest {
    private final String name;
    private final String description;
}
