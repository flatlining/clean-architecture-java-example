package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.usecase.UseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

public class CreateCircularUseCase extends UseCase<CreateCircularUseCase.InputValues, CreateCircularUseCase.OutputValues> {
    private CircularRepository repository;

    public CreateCircularUseCase(CircularRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Circular circular = Circular.builder()
                .withIdentity(Identity.random())
                .withName(input.getName())
                .withDescription(input.getDescription())
                .build();

        return OutputValues.builder()
                .withCircular(repository.create(circular))
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
        private final Circular circular;
    }
}
