package dev.schertel.cq.circular.usecase.output;

import dev.schertel.cq.circular.entity.CircularQueue;

public interface UpdateCircleQueue {
    void update(String id, CircularQueue entity);
}
