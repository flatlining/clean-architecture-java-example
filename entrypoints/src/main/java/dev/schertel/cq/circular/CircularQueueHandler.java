package dev.schertel.cq.circular;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class CircularQueueHandler {
    CircularQueueUseCase useCase;

    public CircularQueueHandler(CircularQueueUseCase useCase) {
        this.useCase = useCase;
    }

    public Flux<CircularQueueDto> getAll() {
        return Flux.fromStream(useCase.getAll().map(c -> CircularQueueDto.from(c)));
    }

    public Mono<CircularQueueDto> getById(String id) {
        return useCase.getById(UUID.fromString(id)).findFirst().map(circularQueue -> Mono.just(CircularQueueDto.from(circularQueue))).orElseGet(Mono::empty);
    }
}
