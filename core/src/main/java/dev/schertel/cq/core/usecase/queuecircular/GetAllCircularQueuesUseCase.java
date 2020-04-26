package dev.schertel.cq.core.usecase.queuecircular;

import dev.schertel.cq.core.domain.CircularQueue;
import dev.schertel.cq.core.usecase.UseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.List;

public class GetAllCircularQueuesUseCase extends UseCase<GetAllCircularQueuesUseCase.InputValues, GetAllCircularQueuesUseCase.OutputValues> {
    private CircularQueueRepository repository;

    public GetAllCircularQueuesUseCase(CircularQueueRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.getAll());
    }

    @Value
    @Builder(setterPrefix = "with")
    @EqualsAndHashCode
    @ToString
    public static class InputValues implements UseCase.InputValues {
    }

    @Value
    @Builder(setterPrefix = "with")
    @EqualsAndHashCode
    @ToString
    public static class OutputValues implements UseCase.OutputValues {
        private final List<CircularQueue> circularQueue;
    }
}
