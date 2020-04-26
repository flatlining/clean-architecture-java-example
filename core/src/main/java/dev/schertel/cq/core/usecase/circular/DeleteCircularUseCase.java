package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.domain.NotFoundException;
import dev.schertel.cq.core.usecase.UseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

public class DeleteCircularUseCase extends UseCase<DeleteCircularUseCase.InputValues, DeleteCircularUseCase.OutputValues> {
    private CircularRepository repository;

    public DeleteCircularUseCase(CircularRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Identity identity = input.getIdentity();

        return repository.deleteByIdentity(identity)
                .map(OutputValues::new)
                .orElseThrow(() -> NotFoundException.of(identity.getId()));
    }

    @Value
    @Builder(setterPrefix = "with")
    @EqualsAndHashCode
    @ToString
    public static class InputValues implements UseCase.InputValues {
        private final Identity identity;
    }

    @Value
    @Builder(setterPrefix = "with")
    @EqualsAndHashCode
    @ToString
    public static class OutputValues implements UseCase.OutputValues {
        private final Circular circular;
    }
}
