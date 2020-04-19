package dev.schertel.cq.circular.usecase;

import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.exception.CircularQueueNotFoundException;
import dev.schertel.cq.circular.usecase.input.CircularQueueDataProvider;
import dev.schertel.cq.circular.usecase.input.IdGenerator;
import dev.schertel.cq.circular.usecase.output.CreateCircleQueue;
import dev.schertel.cq.circular.usecase.output.DeleteCircleQueue;
import dev.schertel.cq.circular.usecase.output.ReadCircleQueue;
import dev.schertel.cq.circular.usecase.output.UpdateCircleQueue;

import java.util.List;
import java.util.Optional;

public class CircularQueueUseCase implements CreateCircleQueue, ReadCircleQueue, UpdateCircleQueue, DeleteCircleQueue {
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

    @Override
    public void update(String id, CircularQueue entity) {
        Optional<CircularQueue> toUpdate = provider.read(id);
        toUpdate.ifPresentOrElse(u -> {
            u.setId(id);
            Optional.ofNullable(entity.getName()).ifPresent(u::setName);
            Optional.ofNullable(entity.getDescription()).ifPresent(u::setDescription);
            provider.update(u);
        }, () -> {
            throw new CircularQueueNotFoundException(id);
        });
    }

    @Override
    public Optional<CircularQueue> replaceOrCreate(String id, CircularQueue entity) {
        Optional<CircularQueue> previous = provider.read(id);
        if (previous.isPresent()) {
            CircularQueue toReplace = new CircularQueue(previous.get().getId(), entity.getName(), entity.getDescription());
            provider.delete(id);
            provider.create(toReplace);
            return Optional.empty();
        } else {
            CircularQueue toCreate = new CircularQueue(id, entity.getName(), entity.getDescription());
            return Optional.of(provider.create(toCreate));
        }
    }

    @Override
    public void delete(String id) {
        provider.delete(id).orElseThrow(() -> new CircularQueueNotFoundException(id));
    }
}
