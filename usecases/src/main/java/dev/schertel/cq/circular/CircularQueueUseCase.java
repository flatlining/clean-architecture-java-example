package dev.schertel.cq.circular;

import java.util.UUID;
import java.util.stream.Stream;

public interface CircularQueueUseCase {
    public Stream<CircularQueue> getAll();

    public CircularQueue getById(UUID id);
}
