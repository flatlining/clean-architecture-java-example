package dev.schertel.cq.circular.dto;

public class CircularQueueRequestDto {
    private String name;
    private String description;

    public CircularQueueRequestDto() {
    }

    public CircularQueueRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
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
}
