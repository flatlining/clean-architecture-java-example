package dev.schertel.cq.entrypoint.rest.queuecircular;

import dev.schertel.cq.entrypoint.rest.entity.CircularQueueRequest;
import dev.schertel.cq.entrypoint.rest.entity.CircularQueueResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/circular")
public interface CircularQueueResource {
    @PostMapping
    CompletableFuture<CircularQueueResponse> create(@RequestBody CircularQueueRequest request);

    @GetMapping
    CompletableFuture<List<CircularQueueResponse>> readAll();

    @GetMapping("/{id}")
    CompletableFuture<CircularQueueResponse> readByIdentity(@PathVariable("id") String id);

    @DeleteMapping
    void deleteAll();

    @DeleteMapping("/{id}")
    void deleteByIdentity(@PathVariable("id") String id);
}