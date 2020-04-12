package dev.schertel.cq.circular;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/circular")
public class CircularQueueController {
    @GetMapping
    private Flux<CircularQueueDto> getAllCircularQueues() {
        CircularQueueDto cq1 = new CircularQueueDto(UUID.randomUUID(), "Queue 1", "Circular queue 1", LocalDateTime.now(), LocalDateTime.now());
        CircularQueueDto cq2 = new CircularQueueDto(UUID.randomUUID(), "Queue 2", "Circular queue 2", LocalDateTime.now(), LocalDateTime.now());
        return (Flux<CircularQueueDto>) Flux.just(cq1, cq2);
    }
}
