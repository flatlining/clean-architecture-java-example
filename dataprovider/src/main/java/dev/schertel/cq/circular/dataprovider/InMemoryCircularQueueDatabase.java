package dev.schertel.cq.circular.dataprovider;

import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.usecase.input.CircularQueueDataProvider;

import java.util.*;

public class InMemoryCircularQueueDatabase implements CircularQueueDataProvider {
    private final Map<String, CircularQueue> inMemoryDatabase = new HashMap<String, CircularQueue>();

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

    @Override
    public void deleteAll() {
        inMemoryDatabase.clear();
    }

    @Override
    public Optional<CircularQueue> delete(String id) {
        return Optional.ofNullable(inMemoryDatabase.remove(id));
    }
}
