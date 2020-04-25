package dev.schertel.cq.circular.usecase;

import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.exception.CircularQueueNotFoundException;
import dev.schertel.cq.circular.usecase.input.CircularQueueDataProvider;
import dev.schertel.cq.circular.usecase.output.CreateCircleQueue;
import dev.schertel.cq.circular.usecase.output.DeleteCircleQueue;
import dev.schertel.cq.circular.usecase.output.ReadCircleQueue;
import dev.schertel.cq.usecase.output.GenerateId;

import java.util.List;

public class CircularQueueUseCase implements CreateCircleQueue, ReadCircleQueue, DeleteCircleQueue {
    private CircularQueueDataProvider provider;
    private GenerateId idGenerator;

    public CircularQueueUseCase(CircularQueueDataProvider provider, GenerateId idGenerator) {
        this.provider = provider;
        this.idGenerator = idGenerator;
    }

    @Override
    public CircularQueue create(CircularQueue entity) {
        CircularQueue toSave = CircularQueue.builder()
                .withId(idGenerator.generate())
                .withName(entity.getName())
                .withDescription(entity.getDescription())
                .build();
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

    @Override
    public void deleteAll() {
        provider.deleteAll();
    }

    @Override
    public void delete(String id) {
        provider.delete(id).orElseThrow(() -> new CircularQueueNotFoundException(id));
    }
}
