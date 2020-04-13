package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueDto;
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
    public CircularQueueDto create(@RequestBody CircularQueueDto body) {
        return handler.create(body);
    }

    @GetMapping
    @ResponseBody
    public List<CircularQueueDto> readAll() {
        return handler.readAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CircularQueueDto read(@PathVariable("id") UUID id) {
        return handler.read(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody CircularQueueDto body) {
        handler.update(id, body);
    }
}
