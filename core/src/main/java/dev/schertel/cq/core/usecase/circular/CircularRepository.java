package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;

import java.util.List;
import java.util.Optional;

public interface CircularRepository {
    Circular create(Circular circular);

    List<Circular> readAll();

    Optional<Circular> readByIdentity(Identity identity);

    List<Circular> deleteAll();

    Optional<Circular> deleteByIdentity(Identity identity);
}
