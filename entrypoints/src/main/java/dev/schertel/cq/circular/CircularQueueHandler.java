package dev.schertel.cq.circular;

import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

public class CircularQueueHandler {
    CircularQueueUseCase useCase;

    public CircularQueueHandler(CircularQueueUseCase useCase) {
        this.useCase = useCase;
    }

    public Flux<CircularQueueDto> getAll() {
        return Flux.fromStream(useCase.getAll().map(c -> CircularQueueDto.from(c)));
    }
}
