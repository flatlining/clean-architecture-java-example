package dev.schertel.cq.circular;

import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

public class CircularQueueHandler {
    public Flux<CircularQueueDto> getAll() {
        CircularQueueDto cq1 = new CircularQueueDto(UUID.randomUUID(), "Queue 1", "Circular queue 1", LocalDateTime.now(), LocalDateTime.now());
        CircularQueueDto cq2 = new CircularQueueDto(UUID.randomUUID(), "Queue 2", "Circular queue 2", LocalDateTime.now(), LocalDateTime.now());
        return Flux.just(cq1, cq2);
    }
}
