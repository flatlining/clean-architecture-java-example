package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.usecase.UseCase;
import dev.schertel.cq.core.usecase.identity.GenerateRandomIdentityUseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

public class CreateCircularUseCase extends UseCase<CreateCircularUseCase.InputValues, CreateCircularUseCase.OutputValues> {
    private CircularRepository repository;
    private GenerateRandomIdentityUseCase generateRandomIdentityUseCase;

    public CreateCircularUseCase(CircularRepository repository, GenerateRandomIdentityUseCase generateRandomIdentityUseCase) {
        this.repository = repository;
        this.generateRandomIdentityUseCase = generateRandomIdentityUseCase;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Identity identity = generateRandomIdentityUseCase.execute(GenerateRandomIdentityUseCase.InputValues.builder().build()).getIdentity();

        Circular circular = Circular.builder()
                .withId(identity)
                .withName(input.getName())
                .withDescription(input.getDescription())
                .build();

        return OutputValues.builder()
                .withCircular(repository.create(circular))
                .build();
    }

    @Value
    @Builder(builderClassName = "Builder", setterPrefix = "with")
    @EqualsAndHashCode
    @ToString
    public static class InputValues implements UseCase.InputValues {
        private final String name;
        private final String description;
    }

    @Value
    @Builder(builderClassName = "Builder", setterPrefix = "with")
    @EqualsAndHashCode
    @ToString
    public static class OutputValues implements UseCase.OutputValues {
        private final Circular circular;
    }
}
