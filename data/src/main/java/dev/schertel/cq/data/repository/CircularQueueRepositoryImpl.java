package dev.schertel.cq.data.repository;

import dev.schertel.cq.core.domain.CircularQueue;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.usecase.queuecircular.CircularQueueRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CircularQueueRepositoryImpl implements CircularQueueRepository {
    HashMap<Identity, CircularQueue> inMemoryDatabase = new HashMap<Identity, CircularQueue>();

    @Override
    public CircularQueue persist(CircularQueue order) {
        inMemoryDatabase.put(order.getId(), order);
        return order;
    }

    @Override
    public List<CircularQueue> getAll() {
        return new ArrayList<CircularQueue>(inMemoryDatabase.values());
    }

    @Override
    public Optional<CircularQueue> getById(Identity id) {
        return Optional.ofNullable(inMemoryDatabase.get(id));
    }

    @Override
    public List<CircularQueue> deleteAll() {
        List<CircularQueue> deleteValues = new ArrayList<CircularQueue>(inMemoryDatabase.values());
        inMemoryDatabase.clear();
        return deleteValues;
    }

    @Override
    public Optional<CircularQueue> deleteById(Identity id) {
        return Optional.ofNullable(inMemoryDatabase.remove(id));
    }
}
