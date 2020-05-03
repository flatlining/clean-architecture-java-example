package dev.schertel.cq.core.usecase.identity;

import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.usecase.UseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.UUID;

public class GenerateRandomIdentityUseCase extends UseCase<GenerateRandomIdentityUseCase.InputValues, GenerateRandomIdentityUseCase.OutputValues> {
    @Override
    public OutputValues execute(InputValues input) {
        return OutputValues.builder()
                .withIdentity(Identity.of(UUID.randomUUID().toString()))
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
        private final Identity identity;
    }
}
