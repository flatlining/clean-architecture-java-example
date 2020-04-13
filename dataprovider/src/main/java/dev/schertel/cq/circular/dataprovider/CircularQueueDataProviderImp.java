package dev.schertel.cq.circular.dataprovider;

import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.usecase.ICircularQueueDataProvider;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CircularQueueDataProviderImp implements ICircularQueueDataProvider {
    List<CircularQueue> data = new ArrayList<CircularQueue>();

    public CircularQueueDataProviderImp() {
        LocalDateTime now = LocalDateTime.now();
        data.add(new CircularQueue(UUID.randomUUID(), "Queue 1", "Circular queue 1", now, now));
        now = LocalDateTime.now();
        data.add(new CircularQueue(UUID.randomUUID(), "Queue 2", "Circular queue 2", now, now));
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

    @Override
    public void update(CircularQueue entity) {
        LocalDateTime now = LocalDateTime.now();
        Optional<CircularQueue> current = data.stream().filter(c -> c.getId().equals(entity.getId())).findFirst();
        if (current.isPresent()) {
            current.get().setName(entity.getName());
            current.get().setDescription(entity.getDescription());
            current.get().setUpdatedAt(now);
        }
    }
}
