package dev.schertel.cq.circular;

import java.util.UUID;
import java.util.stream.Stream;

public interface CircularQueueDataProvider {
    public Stream<CircularQueue> getAll();

    public Stream<CircularQueue> getById(UUID id);
}
