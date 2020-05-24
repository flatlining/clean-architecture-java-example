package dev.schertel.cq.data.repository;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.usecase.circular.CircularRepository;
import dev.schertel.cq.data.database.circular.CircularEntityRepository;
import dev.schertel.cq.data.database.circular.entity.CircularEntity;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CircularRepositoryImpl implements CircularRepository {
    private Logger logger;

    private CircularEntityRepository repository;

    public CircularRepositoryImpl(Logger logger, CircularEntityRepository circularEntityRepository) {
        this.logger = logger;
        this.repository = circularEntityRepository;
    }

    @Override
    public Circular create(Circular circular) {
        logger.info("Creating: {}", circular);

        CircularEntity entity = CircularEntity.builder()
                .withId(circular.getId().getId())
                .withName(circular.getName())
                .withDescription(circular.getDescription())
                .build();

        CircularEntity saved = repository.save(entity);

        Circular created = Circular.builder()
                .withId(Identity.of(saved.getId()))
                .withName(saved.getName())
                .withDescription(saved.getDescription())
                .build();

        logger.info("Created: {}", created);
        return created;
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
