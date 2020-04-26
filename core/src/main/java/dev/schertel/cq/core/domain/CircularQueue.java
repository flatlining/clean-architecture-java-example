package dev.schertel.cq.core.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
@EqualsAndHashCode
@ToString
public class CircularQueue {
    private final Identity id;
    private final String name;
    private final String description;
}
