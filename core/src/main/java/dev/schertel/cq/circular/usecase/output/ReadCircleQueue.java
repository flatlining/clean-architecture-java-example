package dev.schertel.cq.circular.usecase.output;

import dev.schertel.cq.circular.entity.CircularQueue;

import java.util.List;

public interface ReadCircleQueue {
    List<CircularQueue> readAll();

    CircularQueue read(String id);
}
