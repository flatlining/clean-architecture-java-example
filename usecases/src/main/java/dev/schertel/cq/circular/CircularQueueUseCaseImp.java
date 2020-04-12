package dev.schertel.cq.circular;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

public class CircularQueueUseCaseImp implements CircularQueueUseCase {
    @Override
    public Stream<CircularQueue> getAll() {
        CircularQueue cq1 = new CircularQueue(UUID.randomUUID(), "Queue 1", "Circular queue 1", LocalDateTime.now(), LocalDateTime.now());
        CircularQueue cq2 = new CircularQueue(UUID.randomUUID(), "Queue 2", "Circular queue 2", LocalDateTime.now(), LocalDateTime.now());
        return Stream.of(cq1, cq2);
    }
}
