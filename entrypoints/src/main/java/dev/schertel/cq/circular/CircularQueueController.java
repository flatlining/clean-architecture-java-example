package dev.schertel.cq.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/circular")
public class CircularQueueController {
    CircularQueueHandler handler;

    public CircularQueueController(@Autowired CircularQueueHandler handler) {
        this.handler = handler;
    }

    @GetMapping
    private Flux<CircularQueueDto> getAllCircularQueues() {
        return handler.getAll();
    }

    @GetMapping("/{id}")
    private Mono<CircularQueueDto> getById(@PathVariable("id") String id) {
        return handler.getById(id);
    }
}
