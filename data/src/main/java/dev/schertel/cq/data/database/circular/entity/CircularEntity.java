package dev.schertel.cq.data.database.circular.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder(builderClassName = "Builder", setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class CircularEntity {
    @Id
    private String id;
    private String name;
    private String description;
}
