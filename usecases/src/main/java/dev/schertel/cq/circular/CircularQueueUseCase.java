package dev.schertel.cq.circular;

import java.util.List;
import java.util.UUID;

public interface CircularQueueUseCase {
    public List<CircularQueue> getAll();

    public CircularQueue getById(UUID id);
}
