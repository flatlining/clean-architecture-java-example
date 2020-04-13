package dev.schertel.cq.circular;

import java.util.List;
import java.util.UUID;

public interface ICircularQueueDataProvider {
    CircularQueue create(CircularQueue entity);

    List<CircularQueue> readAll();

    CircularQueue read(UUID id);

    void update(CircularQueue entity);
}
