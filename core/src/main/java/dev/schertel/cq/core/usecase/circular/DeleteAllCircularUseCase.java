package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.usecase.UseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

public class DeleteAllCircularUseCase extends UseCase<DeleteAllCircularUseCase.InputValues, DeleteAllCircularUseCase.OutputValues> {
    private CircularRepository repository;

    public DeleteAllCircularUseCase(CircularRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        repository.deleteAll();
        return OutputValues.builder().build();
    }

    @Value
    @Builder(builderClassName = "Builder", setterPrefix = "with")
    @EqualsAndHashCode
    @ToString
    public static class InputValues implements UseCase.InputValues {
    }

    @Value
    @Builder(builderClassName = "Builder", setterPrefix = "with")
    @EqualsAndHashCode
    @ToString
    public static class OutputValues implements UseCase.OutputValues {
    }
}
