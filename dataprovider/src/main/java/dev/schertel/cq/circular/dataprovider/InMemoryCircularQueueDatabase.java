package dev.schertel.cq.circular.dataprovider;

import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.usecase.input.CircularQueueDataProvider;

import java.util.*;

public class InMemoryCircularQueueDatabase implements CircularQueueDataProvider {
    Map<String, CircularQueue> inMemoryDatabase = new HashMap<String, CircularQueue>();

    public InMemoryCircularQueueDatabase() {
        CircularQueue cq1 = new CircularQueue(UUID.randomUUID().toString(), "Name 1", "Description 1");
        CircularQueue cq2 = new CircularQueue(UUID.randomUUID().toString(), "Name 2", "Description 2");
        inMemoryDatabase.put(cq1.getId(), cq1);
        inMemoryDatabase.put(cq2.getId(), cq2);
    }

    @Override
    public CircularQueue create(CircularQueue entity) {
        inMemoryDatabase.put(entity.getId(), entity);
        return inMemoryDatabase.get(entity.getId());
    }

    @Override
    public List<CircularQueue> readAll() {
        return new ArrayList<CircularQueue>(inMemoryDatabase.values());
    }

    @Override
    public Optional<CircularQueue> read(String id) {
        return Optional.ofNullable(inMemoryDatabase.get(id));
    }
}
