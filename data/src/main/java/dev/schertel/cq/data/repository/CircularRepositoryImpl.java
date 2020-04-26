package dev.schertel.cq.data.repository;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.usecase.circular.CircularRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CircularRepositoryImpl implements CircularRepository {
    HashMap<Identity, Circular> inMemoryDatabase = new HashMap<Identity, Circular>();

    @Override
    public Circular create(Circular circular) {
        inMemoryDatabase.put(circular.getId(), circular);
        return circular;
    }

    @Override
    public List<Circular> readAll() {
        return new ArrayList<Circular>(inMemoryDatabase.values());
    }

    @Override
    public Optional<Circular> readByIdentity(Identity identity) {
        return Optional.ofNullable(inMemoryDatabase.get(identity));
    }

    @Override
    public List<Circular> deleteAll() {
        List<Circular> deleteValues = new ArrayList<Circular>(inMemoryDatabase.values());
        inMemoryDatabase.clear();
        return deleteValues;
    }

    @Override
    public Optional<Circular> deleteByIdentity(Identity identity) {
        return Optional.ofNullable(inMemoryDatabase.remove(identity));
    }
}
