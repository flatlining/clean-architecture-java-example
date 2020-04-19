package dev.schertel.cq.circular.usecase.output;

import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.exception.CircularQueueNotFoundException;

import java.util.Optional;

public interface UpdateCircleQueue {
    /**
     * Update the {@link CircularQueue} of matching {@code id}.
     *
     * @param id     The {@code id} of the {@link CircularQueue} to update
     * @param entity The {@link CircularQueue} to update
     * @throws CircularQueueNotFoundException If no matching {@link CircularQueue} was found for {@code id}
     */
    void update(String id, CircularQueue entity) throws CircularQueueNotFoundException;

    /**
     * Replace the {@link CircularQueue} of matching {@code id} with {@code entity}.
     *
     * @param id     The {@code id} of the {@link CircularQueue} to replace
     * @param entity The {@link CircularQueue} to replace with
     * @return The {@link CircularQueue} created, {@code null} if replaced
     */
    Optional<CircularQueue> replaceOrCreate(String id, CircularQueue entity);
}
