package dev.schertel.cq.circular;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class CircularQueueDataProviderImp implements CircularQueueDataProvider {
    private List<CircularQueue> data = new ArrayList<CircularQueue>();

    public CircularQueueDataProviderImp() {
        data.add(new CircularQueue(UUID.randomUUID(), "Queue 1", "Circular queue 1", LocalDateTime.now(), LocalDateTime.now()));
        data.add(new CircularQueue(UUID.randomUUID(), "Queue 2", "Circular queue 2", LocalDateTime.now(), LocalDateTime.now()));
    }

    @Override
    public Stream<CircularQueue> getAll() {
        return data.stream();
    }
}
