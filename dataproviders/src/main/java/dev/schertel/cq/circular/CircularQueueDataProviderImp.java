package dev.schertel.cq.circular;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CircularQueueDataProviderImp implements ICircularQueueDataProvider {
    List<CircularQueue> data = new ArrayList<CircularQueue>();

    public CircularQueueDataProviderImp() {
        data.add(new CircularQueue(UUID.randomUUID(), "Queue 1", "Circular queue 1", LocalDateTime.now(), LocalDateTime.now()));
        data.add(new CircularQueue(UUID.randomUUID(), "Queue 2", "Circular queue 2", LocalDateTime.now(), LocalDateTime.now()));
    }

    @Override
    public CircularQueue create(CircularQueue entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setId(UUID.randomUUID());
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        data.add(entity);
        return entity;
    }

    @Override
    public List<CircularQueue> readAll() {
        return data;
    }

    @Override
    public CircularQueue read(UUID id) {
        return data.stream().filter(c -> id.equals(c.getId())).findAny().orElse(null);
    }
}
