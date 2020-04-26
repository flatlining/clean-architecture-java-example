package dev.schertel.cq.core.usecase.queuecircular;

import dev.schertel.cq.core.domain.CircularQueue;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.domain.NotFoundException;
import dev.schertel.cq.core.usecase.UseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

public class GetCircularQueuesUseCase extends UseCase<GetCircularQueuesUseCase.InputValues, GetCircularQueuesUseCase.OutputValues> {
    private CircularQueueRepository repository;

    public GetCircularQueuesUseCase(CircularQueueRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Identity id = input.getId();

        return repository.getById(id)
                .map(OutputValues::new)
                .orElseThrow(() -> NotFoundException.of(id.getId()));
    }

    @Value
    @Builder(setterPrefix = "with")
    @EqualsAndHashCode
    @ToString
    public static class InputValues implements UseCase.InputValues {
        private final Identity id;
    }

    @Value
    @Builder(setterPrefix = "with")
    @EqualsAndHashCode
    @ToString
    public static class OutputValues implements UseCase.OutputValues {
        private final CircularQueue circularQueue;
    }
}
