package dev.schertel.cq.circular.usecase.output;

import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.exception.CircularQueueNotFoundException;

import java.util.List;

public interface ReadCircleQueue {
    /**
     * Real all existing {@link CircularQueue}
     *
     * @return A list of existing {@link CircularQueue}
     */
    List<CircularQueue> readAll();

    /**
     * Read the {@link CircularQueue} of matching {@code id}.
     *
     * @param id The {@code id} of the {@link CircularQueue} to read
     * @return The {@link CircularQueue} read
     * @throws CircularQueueNotFoundException If no matching {@link CircularQueue} was found for {@code id}
     */
    CircularQueue read(String id) throws CircularQueueNotFoundException;
}
