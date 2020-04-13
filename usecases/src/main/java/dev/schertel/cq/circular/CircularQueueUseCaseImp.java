package dev.schertel.cq.circular;

import java.util.List;
import java.util.UUID;

public class CircularQueueUseCaseImp implements ICircularQueueUseCase {
    ICircularQueueDataProvider provider;

    public CircularQueueUseCaseImp(ICircularQueueDataProvider provider) {
        this.provider = provider;
    }

    @Override
    public CircularQueue create(CircularQueue entity) {
        return provider.create(entity);
    }

    @Override
    public List<CircularQueue> readAll() {
        return provider.readAll();
    }

    @Override
    public CircularQueue read(UUID id) {
        return provider.read(id);
    }
}
