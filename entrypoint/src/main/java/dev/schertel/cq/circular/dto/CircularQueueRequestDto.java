package dev.schertel.cq.circular.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

@JsonDeserialize(builder = CircularQueueRequestDto.Builder.class)
public final class CircularQueueRequestDto {
    private final String name;
    private final String description;

    private CircularQueueRequestDto(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
    }

    public static Builder builder() {
        return Builder.newInstance();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CircularQueueRequestDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CircularQueueRequestDto that = (CircularQueueRequestDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private String description;

        private Builder() {
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CircularQueueRequestDto build() {
            return new CircularQueueRequestDto(this);
        }
    }
}
