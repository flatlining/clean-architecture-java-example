package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.domain.NotFoundException;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(RandomBeansExtension.class)
class DeleteCircularUseCaseTest {

    @Mock
    private CircularRepository repository;

    @InjectMocks
    private DeleteCircularUseCase cut;

    private DeleteCircularUseCase.InputValues.InputValuesBuilder inputBuilder;
    private DeleteCircularUseCase.OutputValues.OutputValuesBuilder outputBuilder;

    @BeforeEach
    void setUp() {
        reset(repository);
        inputBuilder = DeleteCircularUseCase.InputValues.builder();
        outputBuilder = DeleteCircularUseCase.OutputValues.builder();
    }

    @Test
    void successToDelete(@Random Circular deleted) {
        // Background
        doReturn(Optional.of(deleted)).when(repository).deleteByIdentity(deleted.getId());

        // Given
        DeleteCircularUseCase.InputValues input = inputBuilder
                .withIdentity(deleted.getId())
                .build();

        // When
        DeleteCircularUseCase.OutputValues actual = cut.execute(input);

        // Then
        verify(repository, times(1)).deleteByIdentity(deleted.getId());

        DeleteCircularUseCase.OutputValues expected = outputBuilder.build();

        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    void failToDelete(@Random Identity toDelete) {
        // Background
        doReturn(Optional.empty()).when(repository).deleteByIdentity(toDelete);

        // Given
        DeleteCircularUseCase.InputValues input = inputBuilder
                .withIdentity(toDelete)
                .build();

        // When
        NotFoundException actual = assertThrows(NotFoundException.class, () -> {
            cut.execute(input);
        });

        // Then
        verify(repository, times(1)).deleteByIdentity(toDelete);

        NotFoundException expected = NotFoundException.of(toDelete.getId());

        assertThat(actual).isNotNull().isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Nested
    class Input {
        @Test
        void nullInput() {
            // Given

            // When
            DeleteCircularUseCase.InputValues actual = inputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(inputValues -> {
                assertThat(inputValues.getIdentity()).isNull();
            });
        }

        @Test
        void fullInput(@Random Identity identity) {
            // Given
            inputBuilder
                    .withIdentity(identity);

            // When
            DeleteCircularUseCase.InputValues actual = inputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(inputValues -> {
                assertThat(inputValues.getIdentity()).isEqualTo(identity);
            });
        }
    }

    @Nested
    class Output {
        @Test
        void nullInput() {
            // Given

            // When
            DeleteCircularUseCase.OutputValues actual = outputBuilder.build();

            // Then
            assertThat(actual).isNotNull();
        }
    }
}
