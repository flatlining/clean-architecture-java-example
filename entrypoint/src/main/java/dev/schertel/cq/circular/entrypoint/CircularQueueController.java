package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueRequestDto;
import dev.schertel.cq.circular.dto.CircularQueueResponseDto;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/circular")
public class CircularQueueController {
    private Logger logger;
    private CircularQueueHandler handler;

    public CircularQueueController(Logger logger, CircularQueueHandler handler) {
        this.logger = logger;
        this.handler = handler;
    }

    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CircularQueueResponseDto create(@RequestBody CircularQueueRequestDto requestBody) {
        logger.info(requestBody.toString());
        CircularQueueResponseDto responseBody = handler.create(requestBody);
        logger.info(responseBody.toString());
        return responseBody;
    }

    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CircularQueueResponseDto> readAll() {
        return handler.readAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CircularQueueResponseDto read(@PathVariable("id") String id) {
        return handler.read(id);
    }

    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE
    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        handler.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        handler.delete(id);
    }
}
