package dev.schertel.cq.circular.dto;

import java.util.Objects;

public final class CircularQueueResponseDto {
    private final String id;
    private final String name;
    private final String description;

    private CircularQueueResponseDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    public static Builder builder() {
        return Builder.newInstance();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CircularQueueResponseDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CircularQueueResponseDto that = (CircularQueueResponseDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    public static class Builder {
        private String id;
        private String name;
        private String description;

        private Builder() {
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CircularQueueResponseDto build() {
            return new CircularQueueResponseDto(this);
        }
    }
}
