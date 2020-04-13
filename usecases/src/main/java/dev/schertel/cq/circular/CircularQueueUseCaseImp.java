package dev.schertel.cq.circular;

import java.util.List;
import java.util.UUID;

public class CircularQueueUseCaseImp implements CircularQueueUseCase {
    CircularQueueDataProvider provider;

    public CircularQueueUseCaseImp(CircularQueueDataProvider provider) {
        this.provider = provider;
    }

    @Override
    public List<CircularQueue> getAll() {
        return provider.getAll();
    }

    @Override
    public CircularQueue getById(UUID id) {
        return provider.getById(id);
    }
}
