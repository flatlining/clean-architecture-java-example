package dev.schertel.cq.circular.usecase.output;

import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.exception.CircularQueueNotFoundException;

public interface UpdateCircleQueue {
    /**
     * Update the {@link CircularQueue} of matching {@code id}.
     *
     * @param id The {@code id} of the {@link CircularQueue} to update
     * @param entity The {@link CircularQueue} to update
     * @throws CircularQueueNotFoundException If no matching {@link CircularQueue} was found for {@code id}
     */
    void update(String id, CircularQueue entity) throws CircularQueueNotFoundException;
}
