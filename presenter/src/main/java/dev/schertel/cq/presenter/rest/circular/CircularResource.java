package dev.schertel.cq.presenter.rest.circular;

import dev.schertel.cq.presenter.rest.entity.CircularRequest;
import dev.schertel.cq.presenter.rest.entity.CircularResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/circular")
public interface CircularResource {
    @PostMapping
    CompletableFuture<CircularResponse> create(@RequestBody CircularRequest request);

    @GetMapping
    CompletableFuture<List<CircularResponse>> readAll();

    @GetMapping("/{id}")
    CompletableFuture<CircularResponse> readByIdentity(@PathVariable("id") String id);

    @DeleteMapping
    void deleteAll();

    @DeleteMapping("/{id}")
    void deleteByIdentity(@PathVariable("id") String id);
}
