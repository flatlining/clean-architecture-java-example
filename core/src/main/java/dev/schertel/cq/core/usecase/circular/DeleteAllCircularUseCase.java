package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.usecase.UseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.List;

public class DeleteAllCircularUseCase extends UseCase<DeleteAllCircularUseCase.InputValues, DeleteAllCircularUseCase.OutputValues> {
    private CircularRepository repository;

    public DeleteAllCircularUseCase(CircularRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.deleteAll());
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
        private final List<Circular> circular;
    }
}
