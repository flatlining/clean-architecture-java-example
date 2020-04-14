package dev.schertel.cq.circular.usecase;

import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.exception.CircularQueueNotFoundException;
import dev.schertel.cq.circular.usecase.input.CircularQueueDataProvider;
import dev.schertel.cq.circular.usecase.input.IdGenerator;
import dev.schertel.cq.circular.usecase.output.CreateCircleQueue;
import dev.schertel.cq.circular.usecase.output.ReadCircleQueue;

import java.util.List;

public class CircularQueueUseCase implements CreateCircleQueue, ReadCircleQueue {
    private CircularQueueDataProvider provider;
    private IdGenerator idGenerator;

    public CircularQueueUseCase(CircularQueueDataProvider provider, IdGenerator idGenerator) {
        this.provider = provider;
        this.idGenerator = idGenerator;
    }

    @Override
    public CircularQueue create(CircularQueue entity) {
        CircularQueue toSave = new CircularQueue(idGenerator.generate(), entity.getName(), entity.getDescription());
        return provider.create(toSave);
    }

    @Override
    public List<CircularQueue> readAll() {
        return provider.readAll();
    }

    @Override
    public CircularQueue read(String id) {
        return provider.read(id).orElseThrow(() -> new CircularQueueNotFoundException(id));
    }
}
