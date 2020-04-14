package dev.schertel.cq.circular.usecase;

import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.usecase.input.ICircularQueueDataProvider;
import dev.schertel.cq.circular.usecase.input.IdGenerator;
import dev.schertel.cq.circular.usecase.output.ICircularQueueUseCase;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
public class CircularQueueUseCaseImp implements ICircularQueueUseCase {
    @Inject
    ICircularQueueDataProvider provider;
    @Inject
    IdGenerator idGenerator;

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
    public Optional<CircularQueue> read(String id) {
        return provider.read(id);
    }
}
