package dev.schertel.cq.circular;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CircularQueueDataProviderImp implements ICircularQueueDataProvider {
    private List<CircularQueue> data = new ArrayList<CircularQueue>();

    public CircularQueueDataProviderImp() {
        data.add(new CircularQueue(UUID.randomUUID(), "Queue 1", "Circular queue 1", LocalDateTime.now(), LocalDateTime.now()));
        data.add(new CircularQueue(UUID.randomUUID(), "Queue 2", "Circular queue 2", LocalDateTime.now(), LocalDateTime.now()));
    }

    @Override
    public List<CircularQueue> getAll() {
        return data;
    }

    @Override
    public CircularQueue getById(UUID id) {
        return data.stream().filter(c -> id.equals(c.getId())).findAny().orElse(null);
    }
}
