package dev.schertel.cq.circular.usecase.output;

import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.exception.CircularQueueNotFoundException;

public interface DeleteCircleQueue {
    /**
     * Real all existing {@link CircularQueue}
     */
    void deleteAll();

    /**
     * Delete the {@link CircularQueue} of matching {@code id}.
     *
     * @param id The {@code id} of the {@link CircularQueue} to delete
     * @throws CircularQueueNotFoundException If no matching {@link CircularQueue} was found for {@code id}
     */
    void delete(String id) throws CircularQueueNotFoundException;
}
