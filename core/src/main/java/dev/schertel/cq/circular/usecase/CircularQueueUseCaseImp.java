package dev.schertel.cq.circular.usecase;

import dev.schertel.cq.circular.entity.CircularQueue;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

@Named
public class CircularQueueUseCaseImp implements ICircularQueueUseCase {
    @Inject
    ICircularQueueDataProvider provider;

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

    @Override
    public void update(CircularQueue entity) {
        provider.update(entity);
    }
}
