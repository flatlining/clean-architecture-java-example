package dev.schertel.cq.circular.usecase.input;

import dev.schertel.cq.circular.entity.CircularQueue;

import java.util.List;
import java.util.Optional;

public interface CircularQueueDataProvider {
    CircularQueue create(CircularQueue entity);

    List<CircularQueue> readAll();

    Optional<CircularQueue> read(String id);
}
