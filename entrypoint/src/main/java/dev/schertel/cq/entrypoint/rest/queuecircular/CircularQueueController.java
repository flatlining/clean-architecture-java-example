package dev.schertel.cq.entrypoint.rest.queuecircular;

import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.usecase.UseCaseExecutor;
import dev.schertel.cq.core.usecase.queuecircular.*;
import dev.schertel.cq.entrypoint.rest.entity.CircularQueueRequest;
import dev.schertel.cq.entrypoint.rest.entity.CircularQueueResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class CircularQueueController implements CircularQueueResource {
    private UseCaseExecutor useCaseExecutor;
    private CreateCircularQueueUseCase createCircularQueueUseCase;
    private GetAllCircularQueuesUseCase getAllCircularQueuesUseCase;
    private GetCircularQueuesUseCase getCircularQueuesUseCase;
    private DeleteAllCircularQueuesUseCase deleteAllCircularQueuesUseCase;
    private DeleteCircularQueuesUseCase deleteCircularQueuesUseCase;

    public CircularQueueController(UseCaseExecutor useCaseExecutor,
                                   CreateCircularQueueUseCase createCircularQueueUseCase,
                                   GetAllCircularQueuesUseCase getAllCircularQueuesUseCase,
                                   GetCircularQueuesUseCase getCircularQueuesUseCase,
                                   DeleteAllCircularQueuesUseCase deleteAllCircularQueuesUseCase,
                                   DeleteCircularQueuesUseCase deleteCircularQueuesUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createCircularQueueUseCase = createCircularQueueUseCase;
        this.getAllCircularQueuesUseCase = getAllCircularQueuesUseCase;
        this.getCircularQueuesUseCase = getCircularQueuesUseCase;
        this.deleteAllCircularQueuesUseCase = deleteAllCircularQueuesUseCase;
        this.deleteCircularQueuesUseCase = deleteCircularQueuesUseCase;
    }

    @Override
    public CompletableFuture<CircularQueueResponse> create(CircularQueueRequest request) {
        return useCaseExecutor.execute(
                createCircularQueueUseCase,
                CreateCircularQueueUseCase.InputValues.builder()
                        .withName(request.getName())
                        .withDescription(request.getDescription())
                        .build(),
                (output) -> CircularQueueResponse.builder()
                        .withId(output.getCircularQueue().getId().getId())
                        .withName(output.getCircularQueue().getName())
                        .withDescription(output.getCircularQueue().getDescription())
                        .build()
        );
    }

    @Override
    public CompletableFuture<List<CircularQueueResponse>> readAll() {
        return useCaseExecutor.execute(
                getAllCircularQueuesUseCase,
                GetAllCircularQueuesUseCase.InputValues.builder().build(),
                (output) -> Collections.emptyList()
        );
    }

    @Override
    public CompletableFuture<CircularQueueResponse> readByIdentity(String id) {
        return useCaseExecutor.execute(
                getCircularQueuesUseCase,
                GetCircularQueuesUseCase.InputValues.builder()
                        .withId(Identity.of(id))
                        .build(),
                (output) -> CircularQueueResponse.builder()
                        .withId(output.getCircularQueue().getId().getId())
                        .withName(output.getCircularQueue().getName())
                        .withDescription(output.getCircularQueue().getDescription())
                        .build()
        );
    }

    @Override
    public void deleteAll() {
        useCaseExecutor.execute(
                deleteAllCircularQueuesUseCase,
                DeleteAllCircularQueuesUseCase.InputValues.builder().build(),
                (output) -> Collections.emptyList()
        );
    }

    @Override
    public void deleteByIdentity(String id) {
        useCaseExecutor.execute(
                deleteCircularQueuesUseCase,
                DeleteCircularQueuesUseCase.InputValues.builder()
                        .withId(Identity.of(id))
                        .build(),
                (output) -> CircularQueueResponse.builder()
                        .withId(output.getCircularQueue().getId().getId())
                        .withName(output.getCircularQueue().getName())
                        .withDescription(output.getCircularQueue().getDescription())
                        .build()
        );
    }
}
