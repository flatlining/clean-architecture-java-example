package dev.schertel.cq.presenter.rest.circular;

import dev.schertel.cq.presenter.rest.entity.CircularRequest;
import dev.schertel.cq.presenter.rest.entity.CircularResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/circular")
public interface CircularResource {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CompletableFuture<CircularResponse> create(@RequestBody CircularRequest request);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<List<CircularResponse>> readAll();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<CircularResponse> readByIdentity(@PathVariable("id") String id);

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    CompletableFuture<Void> deleteAll();

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    CompletableFuture<Void> deleteByIdentity(@PathVariable("id") String id);
}
