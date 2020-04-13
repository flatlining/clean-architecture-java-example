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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CircularQueueDto create(@RequestBody CircularQueueDto circularQueueDto) {
        return handler.create(circularQueueDto);
    }

    @GetMapping
    public List<CircularQueueDto> readAll() {
        return handler.readAll();
    }

    @GetMapping("/{id}")
    public CircularQueueDto read(@PathVariable("id") UUID id) {
        return handler.read(id);
    }
}
