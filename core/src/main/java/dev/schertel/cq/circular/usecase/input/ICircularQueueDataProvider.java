package dev.schertel.cq.circular.usecase.input;

import dev.schertel.cq.circular.entity.CircularQueue;

import java.util.List;
import java.util.UUID;

public interface ICircularQueueDataProvider {
    CircularQueue create(CircularQueue entity);

    List<CircularQueue> readAll();

    CircularQueue read(UUID id);

    void update(CircularQueue entity);
}
