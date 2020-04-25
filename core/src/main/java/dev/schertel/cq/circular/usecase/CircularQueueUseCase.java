package dev.schertel.cq.circular.usecase;

import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.exception.CircularQueueNotFoundException;
import dev.schertel.cq.circular.usecase.input.CircularQueueDataProvider;
import dev.schertel.cq.circular.usecase.output.CreateCircleQueue;
import dev.schertel.cq.circular.usecase.output.DeleteCircleQueue;
import dev.schertel.cq.circular.usecase.output.ReadCircleQueue;
import dev.schertel.cq.circular.usecase.output.UpdateCircleQueue;
import dev.schertel.cq.usecase.output.GenerateId;

import java.util.List;
import java.util.Optional;

public class CircularQueueUseCase implements CreateCircleQueue, ReadCircleQueue, UpdateCircleQueue, DeleteCircleQueue {
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
    public void update(String id, CircularQueue entity) {
        Optional<CircularQueue> previous = provider.read(id);
        previous.ifPresentOrElse(p -> {
            CircularQueue.Builder updateBuilder = CircularQueue.builder().from(p);
            Optional.ofNullable(entity.getName()).ifPresent(updateBuilder::withName);
            Optional.ofNullable(entity.getDescription()).ifPresent(updateBuilder::withDescription);
            provider.update(updateBuilder.build());
        }, () -> {
            throw new CircularQueueNotFoundException(id);
        });
    }

    @Override
    public Optional<CircularQueue> replaceOrCreate(String id, CircularQueue entity) {
        Optional<CircularQueue> previous = provider.read(id);
        if (previous.isPresent()) {
            CircularQueue toReplace = CircularQueue.builder()
                    .from(previous.get())
                    .withName(entity.getName())
                    .withDescription(entity.getDescription())
                    .build();
            provider.delete(id);
            provider.create(toReplace);
            return Optional.empty();
        } else {
            CircularQueue toCreate = CircularQueue.builder()
                    .withId(id)
                    .withName(entity.getName())
                    .withDescription(entity.getDescription())
                    .build();
            return Optional.of(provider.create(toCreate));
        }
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
