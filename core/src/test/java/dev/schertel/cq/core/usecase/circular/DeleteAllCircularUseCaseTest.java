package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(RandomBeansExtension.class)
class DeleteAllCircularUseCaseTest {

    @Mock
    private CircularRepository repository;

    @InjectMocks
    private DeleteAllCircularUseCase cut;

    private DeleteAllCircularUseCase.InputValues.Builder inputBuilder;
    private DeleteAllCircularUseCase.OutputValues.Builder outputBuilder;

    @BeforeEach
    void setUp() {
        reset(repository);
        inputBuilder = DeleteAllCircularUseCase.InputValues.builder();
        outputBuilder = DeleteAllCircularUseCase.OutputValues.builder();
    }

    @Test
    void successEmptyRepository() {
        // Background
        doReturn(Collections.emptyList()).when(repository).deleteAll();

        // Given
        DeleteAllCircularUseCase.InputValues input = inputBuilder.build();

        // When
        DeleteAllCircularUseCase.OutputValues actual = cut.execute(input);

        // Then
        verify(repository, times(1)).deleteAll();

        DeleteAllCircularUseCase.OutputValues expected = outputBuilder.build();

        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    void successNonEmptyRepository(@Random(size = 5, type = Circular.class) List<Circular> deleted) {
        // Background
        doReturn(deleted).when(repository).deleteAll();

        // Given
        DeleteAllCircularUseCase.InputValues input = inputBuilder.build();

        // When
        DeleteAllCircularUseCase.OutputValues actual = cut.execute(input);

        // Then
        verify(repository, times(1)).deleteAll();

        DeleteAllCircularUseCase.OutputValues expected = outputBuilder.build();

        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Nested
    class Input {
        @Test
        void nullInput() {
            // Given

            // When
            DeleteAllCircularUseCase.InputValues actual = inputBuilder.build();

            // Then
            assertThat(actual).isNotNull();
        }
    }

    @Nested
    class Output {
        @Test
        void nullInput() {
            // Given

            // When
            DeleteAllCircularUseCase.OutputValues actual = outputBuilder.build();

            // Then
            assertThat(actual).isNotNull();
        }
    }
}
