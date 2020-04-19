package dev.schertel.cq.circular.usecase.output;

import dev.schertel.cq.circular.entity.CircularQueue;

public interface CreateCircleQueue {
    /**
     * Create a new {@link CircularQueue} and return it.
     *
     * @param entity The {@link CircularQueue} to create
     * @return The {@link CircularQueue} created
     */
    CircularQueue create(CircularQueue entity);
}
