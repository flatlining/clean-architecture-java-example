package dev.schertel.cq.core.usecase.queuecircular;

import dev.schertel.cq.core.domain.CircularQueue;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.usecase.UseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

public class CreateCircularQueueUseCase extends UseCase<CreateCircularQueueUseCase.InputValues, CreateCircularQueueUseCase.OutputValues> {
    private CircularQueueRepository repository;

    public CreateCircularQueueUseCase(CircularQueueRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        CircularQueue circularQueue = CircularQueue.builder()
                .withId(Identity.random())
                .withName(input.getName())
                .withDescription(input.getDescription())
                .build();
        return OutputValues.builder()
                .withCircularQueue(repository.persist(circularQueue))
                .build();
    }

    @Value
    @Builder(setterPrefix = "with")
    @EqualsAndHashCode
    @ToString
    public static class InputValues implements UseCase.InputValues {
        private final String name;
        private final String description;
    }

    @Value
    @Builder(setterPrefix = "with")
    @EqualsAndHashCode
    @ToString
    public static class OutputValues implements UseCase.OutputValues {
        private final CircularQueue circularQueue;
    }
}
