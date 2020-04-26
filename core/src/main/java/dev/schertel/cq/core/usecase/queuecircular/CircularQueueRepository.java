package dev.schertel.cq.core.usecase.queuecircular;

import dev.schertel.cq.core.domain.CircularQueue;
import dev.schertel.cq.core.domain.Identity;

import java.util.List;
import java.util.Optional;

public interface CircularQueueRepository {
    CircularQueue persist(CircularQueue order);

    List<CircularQueue> getAll();

    Optional<CircularQueue> getById(Identity id);

    List<CircularQueue> deleteAll();

    Optional<CircularQueue> deleteById(Identity id);
}
