package dev.schertel.cq.circular;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class CircularQueueDto {
    UUID id;
    String name;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public static CircularQueueDto from(CircularQueue cq) {
        return Optional.of(cq).map(circularQueue -> new CircularQueueDto(circularQueue.getId(), circularQueue.getName(), circularQueue.getDescription(), circularQueue.getCreatedAt(), circularQueue.getUpdatedAt())).orElse(null);
    }

    public static CircularQueueDto from(Optional<CircularQueue> cq) {
        return cq.map(circularQueue -> new CircularQueueDto(circularQueue.getId(), circularQueue.getName(), circularQueue.getDescription(), circularQueue.getCreatedAt(), circularQueue.getUpdatedAt())).orElse(null);
    }

    public CircularQueueDto() {
    }

    public CircularQueueDto(UUID id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
