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
import java.util.Optional;

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
            // Given

            // When
            Circular actual = cut.create(expected);

            // Then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class RealAll {
        @Test
        void realAllEmpty() {
            // Given

            // When
            List<Circular> actual = cut.readAll();

            // Then
            assertThat(actual).isEmpty();
        }

        @Test
        void realAllNonEmpty(@Random(size = 5, type = Circular.class) List<Circular> repository) {
            // Background
            repository.forEach(circular -> {
                cut.create(circular);
            });

            // Given

            // When
            List<Circular> actual = cut.readAll();

            // Then
            assertThat(actual).containsExactlyInAnyOrderElementsOf(repository);
        }
    }

    @Nested
    class ReadByIdentity {
        @Test
        void readByIdentityEmpty(@Random Identity randomIdentity) {
            // Given

            // When
            Optional<Circular> actual = cut.readByIdentity(randomIdentity);

            // Then
            assertThat(actual).isNotPresent();
        }

        @Test
        void readByIdentityNonEmpty(@Random(size = 5, type = Circular.class) List<Circular> repository) {
            // Background
            repository.forEach(circular -> {
                cut.create(circular);
            });
            assertThat(cut.readAll()).containsExactlyInAnyOrderElementsOf(repository);

            // Given
            java.util.Random rand = new java.util.Random();
            Circular randomItem = repository.get(rand.nextInt(repository.size()));

            // When
            Optional<Circular> actual = cut.readByIdentity(randomItem.getId());

            // Then
            assertThat(actual).isPresent().hasValue(randomItem);
        }

        @Test
        void readByIdentityNonExistent(@Random(size = 5, type = Circular.class) List<Circular> repository, @Random Identity randomIdentity) {
            // Background
            repository.forEach(circular -> {
                cut.create(circular);
            });
            assertThat(cut.readAll()).containsExactlyInAnyOrderElementsOf(repository);

            // Given

            // When
            Optional<Circular> actual = cut.readByIdentity(randomIdentity);

            // Then
            assertThat(actual).isNotPresent();
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