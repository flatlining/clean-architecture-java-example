package dev.schertel.cq.core.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder", setterPrefix = "with")
public class Circular {
    Identity id;
    String name;
    String description;
}
