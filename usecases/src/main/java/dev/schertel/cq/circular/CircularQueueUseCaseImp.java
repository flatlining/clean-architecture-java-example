package dev.schertel.cq.circular;

import java.util.UUID;
import java.util.stream.Stream;

public class CircularQueueUseCaseImp implements CircularQueueUseCase {
    CircularQueueDataProvider provider;

    public CircularQueueUseCaseImp(CircularQueueDataProvider provider) {
        this.provider = provider;
    }

    @Override
    public Stream<CircularQueue> getAll() {
        return provider.getAll();
    }

    @Override
    public CircularQueue getById(UUID id) {
        return provider.getById(id);
    }
}
