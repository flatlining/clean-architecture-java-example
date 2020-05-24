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
        logger.info("create({})", circular);

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

        logger.info("create(): {}", created);
        return created;
    }

    @Override
    public List<Circular> readAll() {
        List<Circular> existing = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(t -> Circular.builder()
                        .withId(Identity.of(t.getId()))
                        .withName(t.getName())
                        .withDescription(t.getDescription())
                        .build()
                ).collect(Collectors.toList());

        logger.info("readAll(): {}", existing);
        return existing;
    }

    @Override
    public Optional<Circular> readByIdentity(Identity identity) {
        logger.info("readByIdentity({})", identity);

        Optional<CircularEntity> ce = repository.findById(identity.getId());
        Optional<Circular> read = ce.map(t -> Circular.builder()
                .withId(Identity.of(t.getId()))
                .withName(t.getName())
                .withDescription(t.getDescription())
                .build());

        logger.info("readByIdentity(): {}", read);
        return read;
    }

    @Override
    public List<Circular> deleteAll() {
        List<Circular> deleted = readAll();
        repository.deleteAll();

        logger.info("deleteAll(): {}", deleted);
        return deleted;
    }

    @Override
    public Optional<Circular> deleteByIdentity(Identity identity) {
        logger.info("deleteByIdentity({})", identity);

        Optional<Circular> toDelete = readByIdentity(identity);
        if (toDelete.isPresent()) {
            repository.deleteById(identity.getId());
        }

        logger.info("deleteByIdentity(): {}", toDelete);
        return toDelete;
    }
}
