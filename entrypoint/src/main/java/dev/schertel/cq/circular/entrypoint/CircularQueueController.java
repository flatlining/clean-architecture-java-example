package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<CircularQueueDto> read(@PathVariable("id") String id) {
        return handler.read(id);
    }
}
