package dev.schertel.cq.circular;

import java.util.stream.Stream;

public interface CircularQueueDataProvider {
    public Stream<CircularQueue> getAll();
}
