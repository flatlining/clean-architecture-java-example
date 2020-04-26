package dev.schertel.cq.presenter.rest.circular;

import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.usecase.UseCaseExecutor;
import dev.schertel.cq.core.usecase.circular.*;
import dev.schertel.cq.presenter.rest.entity.CircularRequest;
import dev.schertel.cq.presenter.rest.entity.CircularResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class CircularController implements CircularResource {
    private final UseCaseExecutor useCaseExecutor;
    private final CreateCircularUseCase createCircularUseCase;
    private final ReadAllCircularUseCase readAllCircularUseCase;
    private final ReadCircularUseCase readCircularUseCase;
    private final DeleteAllCircularUseCase deleteAllCircularUseCase;
    private final DeleteCircularUseCase deleteCircularUseCase;

    public CircularController(UseCaseExecutor useCaseExecutor,
                              CreateCircularUseCase createCircularUseCase,
                              ReadAllCircularUseCase readAllCircularUseCase,
                              ReadCircularUseCase readCircularUseCase,
                              DeleteAllCircularUseCase deleteAllCircularUseCase,
                              DeleteCircularUseCase deleteCircularUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createCircularUseCase = createCircularUseCase;
        this.readAllCircularUseCase = readAllCircularUseCase;
        this.readCircularUseCase = readCircularUseCase;
        this.deleteAllCircularUseCase = deleteAllCircularUseCase;
        this.deleteCircularUseCase = deleteCircularUseCase;
    }

    @Override
    public CompletableFuture<CircularResponse> create(CircularRequest request) {
        return useCaseExecutor.execute(
                createCircularUseCase,
                CreateCircularUseCase.InputValues.builder()
                        .withName(request.getName())
                        .withDescription(request.getDescription())
                        .build(),
                (output) -> CircularResponse.builder()
                        .withId(output.getCircular().getId().getId())
                        .withName(output.getCircular().getName())
                        .withDescription(output.getCircular().getDescription())
                        .build()
        );
    }

    @Override
    public CompletableFuture<List<CircularResponse>> readAll() {
        return useCaseExecutor.execute(
                readAllCircularUseCase,
                ReadAllCircularUseCase.InputValues.builder().build(),
                (output) -> Collections.emptyList()
        );
    }

    @Override
    public CompletableFuture<CircularResponse> readByIdentity(String id) {
        return useCaseExecutor.execute(
                readCircularUseCase,
                ReadCircularUseCase.InputValues.builder()
                        .withIdentity(Identity.of(id))
                        .build(),
                (output) -> CircularResponse.builder()
                        .withId(output.getCircular().getId().getId())
                        .withName(output.getCircular().getName())
                        .withDescription(output.getCircular().getDescription())
                        .build()
        );
    }

    @Override
    public void deleteAll() {
        useCaseExecutor.execute(
                deleteAllCircularUseCase,
                DeleteAllCircularUseCase.InputValues.builder().build(),
                (output) -> Collections.emptyList()
        );
    }

    @Override
    public void deleteByIdentity(String id) {
        useCaseExecutor.execute(
                deleteCircularUseCase,
                DeleteCircularUseCase.InputValues.builder()
                        .withIdentity(Identity.of(id))
                        .build(),
                (output) -> CircularResponse.builder()
                        .withId(output.getCircular().getId().getId())
                        .withName(output.getCircular().getName())
                        .withDescription(output.getCircular().getDescription())
                        .build()
        );
    }
}
