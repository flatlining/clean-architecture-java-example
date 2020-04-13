package dev.schertel.cq.circular;

import java.util.List;
import java.util.UUID;

public class CircularQueueUseCaseImp implements ICircularQueueUseCase {
    ICircularQueueDataProvider provider;

    public CircularQueueUseCaseImp(ICircularQueueDataProvider provider) {
        this.provider = provider;
    }

    @Override
    public List<CircularQueue> getAll() {
        return provider.getAll();
    }

    @Override
    public CircularQueue create(CircularQueue entity) {
        return provider.create(entity);
    }

    @Override
    public CircularQueue getById(UUID id) {
        return provider.getById(id);
    }
}
