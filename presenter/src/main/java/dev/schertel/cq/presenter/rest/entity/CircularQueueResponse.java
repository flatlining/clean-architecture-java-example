package dev.schertel.cq.presenter.rest.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
@EqualsAndHashCode
@ToString
public class CircularQueueResponse {
    private final String id;
    private final String name;
    private final String description;
}
