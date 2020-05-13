package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;

import java.util.List;
import java.util.Optional;

public interface CircularRepository {
    /**
     * Create a {@link Circular}
     *
     * @param circular The {@link Circular} to create
     * @return The {@link Circular} created
     */
    Circular create(Circular circular);

    /**
     * Read all existing {@link Circular}
     *
     * @return A list of read {@link Circular}, empty list if none exist
     */
    List<Circular> readAll();

    /**
     * Read a {@link Circular} by {@link Identity}
     *
     * @param identity The {@link Identity} to read the {@link Circular}
     * @return The {@link Circular} found for {@link Identity}, empty {@link Optional} if none exist
     */
    Optional<Circular> readByIdentity(Identity identity);

    /**
     * Delete all existing {@link Circular}
     *
     * @return A list of deleted {@link Circular}, empty list if none deleted
     */
    List<Circular> deleteAll();

    /**
     * Delete a {@link Circular} by {@link Identity}
     *
     * @param identity The {@link Identity} to delete the {@link Circular}
     * @return The {@link Circular} deleted for {@link Identity}, empty {@link Optional} if none deleted
     */
    Optional<Circular> deleteByIdentity(Identity identity);
}
