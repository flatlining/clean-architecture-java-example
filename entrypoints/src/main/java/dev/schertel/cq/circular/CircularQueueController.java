package dev.schertel.cq.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CircularQueueDto create(@RequestBody CircularQueueDto circularQueueDto) {
        return handler.create(circularQueueDto);
    }

    @GetMapping("/{id}")
    private CircularQueueDto getById(@PathVariable("id") UUID id) {
        return handler.getById(id);
    }
}
