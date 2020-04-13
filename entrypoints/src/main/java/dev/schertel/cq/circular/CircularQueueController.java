package dev.schertel.cq.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/circular")
public class CircularQueueController {
    @Autowired
    CircularQueueHandler handler;

    @GetMapping
    private List<CircularQueueDto> getAllCircularQueues() {
        return handler.getAll();
    }

    @GetMapping("/{id}")
    private CircularQueueDto getById(@PathVariable("id") UUID id) {
        return handler.getById(id);
    }
}
