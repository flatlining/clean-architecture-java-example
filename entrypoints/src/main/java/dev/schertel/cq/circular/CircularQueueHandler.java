package dev.schertel.cq.circular;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CircularQueueHandler {
    CircularQueueUseCase useCase;

    public CircularQueueHandler(CircularQueueUseCase useCase) {
        this.useCase = useCase;
    }

    public List<CircularQueueDto> getAll() {
        return useCase.getAll().stream().map(c -> CircularQueueDto.from(c)).collect(Collectors.toList());
    }

    public CircularQueueDto getById(String id) {
        return CircularQueueDto.from(useCase.getById(UUID.fromString(id)));
    }
}
