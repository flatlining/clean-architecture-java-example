package dev.schertel.cq.data.repository;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomBeansExtension.class)
class CircularRepositoryImplTest {
    @Mock
    private Logger logger;

    private CircularRepositoryImpl cut;

    @BeforeEach
    void setUp() {
        this.cut = new CircularRepositoryImpl(logger);
    }

    @Test
    void create(@Random Circular expected) {
        assertThat(cut.create(expected)).isEqualTo(expected);
    }

    @Test
    void realAllEmpty() {
        assertThat(cut.readAll()).isEmpty();
    }

    @Test
    void realAllNonEmpty(@Random(size = 5, type = Circular.class) List<Circular> expected) {
        expected.forEach(circular -> {
            cut.create(circular);
        });

        List<Circular> actual = cut.readAll();

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void readByIdentityEmpty(@Random Identity identity) {
        Optional<Circular> actual = cut.readByIdentity(identity);

        assertThat(actual).isNotPresent();
    }

    @Test
    void readByIdentityNonEmpty(@Random(size = 5, type = Circular.class) List<Circular> repository) {
        repository.forEach(circular -> {
            cut.create(circular);
        });

        java.util.Random rand = new java.util.Random();
        Circular expected = repository.get(rand.nextInt(repository.size()));

        Optional<Circular> actual = cut.readByIdentity(expected.getId());

        assertThat(actual).isPresent().hasValue(expected);
    }
}