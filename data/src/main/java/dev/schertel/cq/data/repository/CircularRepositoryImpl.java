package dev.schertel.cq.data.repository;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.usecase.circular.CircularRepository;
import dev.schertel.cq.data.database.circular.CircularEntityRepository;
import dev.schertel.cq.data.database.circular.entity.CircularEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CircularRepositoryImpl implements CircularRepository {

    private CircularEntityRepository repository;

    public CircularRepositoryImpl(CircularEntityRepository circularEntityRepository) {
        this.repository = circularEntityRepository;
    }

    @Override
    public Circular create(Circular circular) {
        CircularEntity entity = new CircularEntity();
        entity.setId(circular.getId().getId());
        entity.setName(circular.getName());
        entity.setDescription(circular.getDescription());
        CircularEntity saved = repository.save(entity);
        return Circular.builder()
                .withId(Identity.of(saved.getId()))
                .withName(saved.getName())
                .withDescription(saved.getDescription())
                .build();
    }

    @Override
    public List<Circular> readAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).map(t -> Circular.builder()
                .withId(Identity.of(t.getId()))
                .withName(t.getName())
                .withDescription(t.getDescription())
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<Circular> readByIdentity(Identity identity) {
        Optional<CircularEntity> ce = repository.findById(identity.getId());
        return ce.map(t -> Circular.builder()
                .withId(Identity.of(t.getId()))
                .withName(t.getName())
                .withDescription(t.getDescription())
                .build());
    }

    @Override
    public List<Circular> deleteAll() {
        List<Circular> deleteValues = readAll();
        repository.deleteAll();
        return deleteValues;
    }

    @Override
    public Optional<Circular> deleteByIdentity(Identity identity) {
        Optional<Circular> toDelete = readByIdentity(identity);
        if (toDelete.isPresent()) {
            repository.deleteById(identity.getId());
        }
        return toDelete;
    }
}
