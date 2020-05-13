package dev.schertel.cq.data.repository;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.data.repository.circular.CircularEntityRepository;
import dev.schertel.cq.data.repository.circular.entity.CircularEntity;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;

@ExtendWith(RandomBeansExtension.class)
@ExtendWith(MockitoExtension.class)
class CircularRepositoryImplTest {

    @Mock
    private CircularEntityRepository circularEntityRepository;

    private CircularRepositoryImpl cut;

    @BeforeEach
    void setUp() {
        reset(circularEntityRepository);
        this.cut = new CircularRepositoryImpl(circularEntityRepository);
    }

    @Nested
    class Create {
        @Test
        void create(@Random CircularEntity existing) {
            // Background
            doReturn(existing)
                    .when(circularEntityRepository).save(existing);

            // Given
            Circular toCreate = Circular.builder()
                    .withId(Identity.of(existing.getId()))
                    .withName(existing.getName())
                    .withDescription(existing.getDescription())
                    .build();

            // When
            Circular actual = cut.create(toCreate);

            // Then
            Circular expected = Circular.builder()
                    .withId(Identity.of(existing.getId()))
                    .withName(existing.getName())
                    .withDescription(existing.getDescription())
                    .build();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class RealAll {
        @Test
        void realAllEmpty() {
            // Background
            doReturn(Collections.emptyList())
                    .when(circularEntityRepository).findAll();

            // Given

            // When
            List<Circular> actual = cut.readAll();

            // Then
            assertThat(actual).isEmpty();
        }

        @Test
        void realAllNonEmpty(@Random(size = 5, type = CircularEntity.class) List<CircularEntity> existing) {
            // Background
            doReturn(existing)
                    .when(circularEntityRepository).findAll();

            // Given

            // When
            List<Circular> actual = cut.readAll();

            // Then
            List<Circular> expected = existing.stream()
                    .map(e -> Circular.builder()
                            .withId(Identity.of(e.getId()))
                            .withName(e.getName())
                            .withDescription(e.getDescription())
                            .build())
                    .collect(Collectors.toList());

            assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
        }
    }

    @Nested
    class ReadByIdentity {
        @Test
        void readByIdentityNonExistent(@Random Identity nonExistentIdentity) {
            // Background
            doReturn(Optional.empty())
                    .when(circularEntityRepository).findById(nonExistentIdentity.getId());

            // Given

            // When
            Optional<Circular> actual = cut.readByIdentity(nonExistentIdentity);

            // Then
            assertThat(actual).isNotPresent();
        }

        @Test
        void readByIdentityExistent(@Random CircularEntity existing) {
            // Background
            doReturn(Optional.of(existing))
                    .when(circularEntityRepository).findById(existing.getId());

            // Given
            Identity existentIdentity = Identity.of(existing.getId());

            // When
            Optional<Circular> actual = cut.readByIdentity(existentIdentity);

            // Then
            Circular expected = Circular.builder()
                    .withId(existentIdentity)
                    .withName(existing.getName())
                    .withDescription(existing.getDescription())
                    .build();

            assertThat(actual).isPresent().contains(expected);
        }
    }

    @Disabled("Need to mock circularEntityRepository behavior")
    @Nested
    class DeleteAll {
        @Test
        void deleteAllEmpty() {
            // Given

            // When
            List<Circular> actual = cut.deleteAll();

            // Then
            assertThat(actual).isEmpty();
        }

        @Test
        void deleteAllNonEmpty(@Random(size = 5, type = Circular.class) List<Circular> repository) {
            // Background
            repository.forEach(circular -> {
                cut.create(circular);
            });
            assertThat(cut.readAll()).containsExactlyInAnyOrderElementsOf(repository);

            // Given
            List<Circular> actual = cut.deleteAll();

            // Then
            assertThat(actual).containsExactlyInAnyOrderElementsOf(repository);
        }
    }

    @Disabled("Need to mock circularEntityRepository behavior")
    @Nested
    class DeleteByIdentity {
        @Test
        void deleteByIdentityEmpty(@Random Identity randomIdentity) {
            // Given

            // When
            Optional<Circular> actual = cut.deleteByIdentity(randomIdentity);

            // Then
            assertThat(actual).isNotPresent();
        }

        @Test
        void deleteByIdentityNonEmpty(@Random(size = 5, type = Circular.class) List<Circular> repository) {
            // Background
            repository.forEach(circular -> {
                cut.create(circular);
            });
            assertThat(cut.readAll()).containsExactlyInAnyOrderElementsOf(repository);

            // Given
            java.util.Random rand = new java.util.Random();
            Circular randomItem = repository.get(rand.nextInt(repository.size()));

            // When
            Optional<Circular> actual = cut.deleteByIdentity(randomItem.getId());

            // Then
            assertThat(actual).isPresent().hasValue(randomItem);
        }

        @Test
        void deleteByIdentityNonExistent(@Random(size = 5, type = Circular.class) List<Circular> repository, @Random Identity randomIdentity) {
            // Background
            repository.forEach(circular -> {
                cut.create(circular);
            });
            assertThat(cut.readAll()).containsExactlyInAnyOrderElementsOf(repository);

            // Given

            // When
            Optional<Circular> actual = cut.deleteByIdentity(randomIdentity);

            // Then
            assertThat(actual).isNotPresent();
        }
    }
}