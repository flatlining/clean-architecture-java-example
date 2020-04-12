package dev.schertel.cq.circular;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/circular")
public class CircularQueueController {
    @GetMapping
    private Flux<CircularQueueDto> getAllCircularQueues() {
        CircularQueueDto cq1 = new CircularQueueDto();
        CircularQueueDto cq2 = new CircularQueueDto();
        return (Flux<CircularQueueDto>) Flux.just(cq1, cq2);
    }
}
