package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueRequestDto;
import dev.schertel.cq.circular.dto.CircularQueueResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/circular")
public class CircularQueueController {
    private CircularQueueHandler handler;

    public CircularQueueController(CircularQueueHandler handler) {
        this.handler = handler;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CircularQueueResponseDto create(@RequestBody CircularQueueRequestDto body) {
        return handler.create(body);
    }

    @GetMapping
    @ResponseBody
    public List<CircularQueueResponseDto> readAll() {
        return handler.readAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CircularQueueResponseDto read(@PathVariable("id") String id) {
        return handler.read(id);
    }
}
