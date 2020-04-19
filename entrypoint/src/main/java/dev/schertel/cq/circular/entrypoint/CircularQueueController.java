package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueRequestDto;
import dev.schertel.cq.circular.dto.CircularQueueResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CircularQueueResponseDto create(@RequestBody CircularQueueRequestDto body) {
        return handler.create(body);
    }

    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CircularQueueResponseDto> readAll() {
        return handler.readAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CircularQueueResponseDto read(@PathVariable("id") String id) {
        return handler.read(id);
    }

    /**
     * https://pt.stackoverflow.com/questions/92870/qual-%C3%A9-a-diferen%C3%A7a-entre-o-m%C3%A9todo-put-e-o-post
     * https://stackoverflow.com/questions/107390/whats-the-difference-between-a-post-and-a-put-http-request
     * https://restfulapi.net/rest-put-vs-post/
     * https://en.wikipedia.org/wiki/Patch_verb#PUT_vs_PATCH_vs_POST
     */

    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PATCH
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody CircularQueueRequestDto body) {
        handler.update(id, body);
    }

    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PUT
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Optional<CircularQueueResponseDto>> replaceOrCreate(@PathVariable("id") String id, @RequestBody CircularQueueRequestDto body) {
        Optional<CircularQueueResponseDto> response = handler.replaceOrCreate(id, body);
        if (response.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        handler.delete(id);
    }
}
