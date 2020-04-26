package dev.schertel.cq.core.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
@EqualsAndHashCode
@ToString
public class Circular {
    private final Identity identity;
    private final String name;
    private final String description;
}
