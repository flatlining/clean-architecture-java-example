package dev.schertel.cq.data.repository;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomBeansExtension.class)
class CircularRepositoryImplTest {

    private CircularRepositoryImpl cut;

    @BeforeEach
    void setUp() {
        this.cut = new CircularRepositoryImpl();
    }

    @Nested
    class Create {
        @Test
        void create(@Random Circular expected) {
            assertThat(cut.create(expected)).isEqualTo(expected);
        }
    }

    @Nested
    class RealAll {
        @Test
        void realAllEmpty() {
            assertThat(cut.readAll()).isEmpty();
        }

        @Test
        void realAllNonEmpty(@Random(size = 5, type = Circular.class) List<Circular> repository) {
            repository.forEach(circular -> {
                cut.create(circular);
            });

            assertThat(cut.readAll()).containsExactlyInAnyOrderElementsOf(repository);
        }
    }

    @Nested
    class ReadByIdentity {
        @Test
        void readByIdentityEmpty(@Random Identity randomIdentity) {
            assertThat(cut.readByIdentity(randomIdentity)).isNotPresent();
        }

        @Test
        void readByIdentityNonEmpty(@Random(size = 5, type = Circular.class) List<Circular> repository) {
            repository.forEach(circular -> {
                cut.create(circular);
            });
            assertThat(cut.readAll()).containsExactlyInAnyOrderElementsOf(repository);

            java.util.Random rand = new java.util.Random();
            Circular randomItem = repository.get(rand.nextInt(repository.size()));

            assertThat(cut.readByIdentity(randomItem.getId())).isPresent().hasValue(randomItem);
        }

        @Test
        void readByIdentityNonExistent(@Random(size = 5, type = Circular.class) List<Circular> repository, @Random Identity randomIdentity) {
            repository.forEach(circular -> {
                cut.create(circular);
            });
            assertThat(cut.readAll()).containsExactlyInAnyOrderElementsOf(repository);

            assertThat(cut.readByIdentity(randomIdentity)).isNotPresent();
        }
    }

    @Nested
    class DeleteAll {
        @Test
        void deleteAllEmpty() {
            assertThat(cut.deleteAll()).isEmpty();
        }

        @Test
        void deleteAllNonEmpty(@Random(size = 5, type = Circular.class) List<Circular> repository) {
            repository.forEach(circular -> {
                cut.create(circular);
            });
            assertThat(cut.readAll()).containsExactlyInAnyOrderElementsOf(repository);

            assertThat(cut.deleteAll()).containsExactlyInAnyOrderElementsOf(repository);

            assertThat(cut.deleteAll()).isEmpty();
        }
    }

    @Nested
    class DeleteByIdentity {
        @Test
        void deleteByIdentityEmpty(@Random Identity randomIdentity) {
            assertThat(cut.deleteByIdentity(randomIdentity)).isNotPresent();
        }

        @Test
        void deleteByIdentityNonEmpty(@Random(size = 5, type = Circular.class) List<Circular> repository) {
            repository.forEach(circular -> {
                cut.create(circular);
            });
            assertThat(cut.readAll()).containsExactlyInAnyOrderElementsOf(repository);

            java.util.Random rand = new java.util.Random();
            Circular randomItem = repository.get(rand.nextInt(repository.size()));

            assertThat(cut.deleteByIdentity(randomItem.getId())).isPresent().hasValue(randomItem);
        }

        @Test
        void deleteByIdentityNonExistent(@Random(size = 5, type = Circular.class) List<Circular> repository, @Random Identity randomIdentity) {
            repository.forEach(circular -> {
                cut.create(circular);
            });
            assertThat(cut.readAll()).containsExactlyInAnyOrderElementsOf(repository);

            assertThat(cut.deleteByIdentity(randomIdentity)).isNotPresent();
        }
    }
}