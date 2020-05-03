package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.usecase.UseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.List;

public class ReadAllCircularUseCase extends UseCase<ReadAllCircularUseCase.InputValues, ReadAllCircularUseCase.OutputValues> {
    private CircularRepository repository;

    public ReadAllCircularUseCase(CircularRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return OutputValues.builder()
                .withCircular(repository.readAll())
                .build();
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
        private final List<Circular> circular;
    }
}
