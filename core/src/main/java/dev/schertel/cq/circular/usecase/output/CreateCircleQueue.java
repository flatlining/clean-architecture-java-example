package dev.schertel.cq.circular.usecase.output;

import dev.schertel.cq.circular.entity.CircularQueue;

public interface CreateCircleQueue {
    CircularQueue create(CircularQueue entity);
}
