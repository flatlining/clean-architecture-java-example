package dev.schertel.cq.circular.entity;

import java.util.Objects;

public class CircularQueue {
    private String id;
    private String name;
    private String description;

    private CircularQueue(Builder builder) {
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
        final StringBuilder sb = new StringBuilder("CircularQueue{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CircularQueue)) return false;
        CircularQueue that = (CircularQueue) o;
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

        public Builder from(CircularQueue circularQueue) {
            this.id = circularQueue.getId();
            this.name = circularQueue.getName();
            this.description = circularQueue.getDescription();
            return this;
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

        public CircularQueue build() {
            return new CircularQueue(this);
        }
    }
}
