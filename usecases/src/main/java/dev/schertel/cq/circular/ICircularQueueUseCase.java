package dev.schertel.cq.circular;

import java.util.List;
import java.util.UUID;

public interface ICircularQueueUseCase {
    public CircularQueue create(CircularQueue entity);

    public List<CircularQueue> readAll();

    public CircularQueue read(UUID id);
}
