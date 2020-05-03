package dev.schertel.cq.core.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder", setterPrefix = "with")
@EqualsAndHashCode
@ToString
public class Identity {
    private final String id;

    public static Identity of(String value) {
        return Identity.builder().withId(value).build();
    }

    public static Identity random() {
        return Identity.builder().withId(UUID.randomUUID().toString()).build();
    }
}
