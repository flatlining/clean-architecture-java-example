package dev.schertel.cq.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
}
