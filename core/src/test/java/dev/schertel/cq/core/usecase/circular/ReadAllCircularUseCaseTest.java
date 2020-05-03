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
class ReadAllCircularUseCaseTest {

    @Mock
    private CircularRepository repository;

    @InjectMocks
    private ReadAllCircularUseCase cut;

    private ReadAllCircularUseCase.InputValues.Builder inputBuilder;
    private ReadAllCircularUseCase.OutputValues.Builder outputBuilder;

    @BeforeEach
    void setUp() {
        reset(repository);
        inputBuilder = ReadAllCircularUseCase.InputValues.builder();
        outputBuilder = ReadAllCircularUseCase.OutputValues.builder();
    }

    @Test
    void readAllEmptyRepository() {
        // Background
        doReturn(Collections.emptyList()).when(repository).readAll();

        // Given
        ReadAllCircularUseCase.InputValues input = inputBuilder.build();

        // When
        ReadAllCircularUseCase.OutputValues actual = cut.execute(input);

        // Then
        verify(repository, times(1)).readAll();

        ReadAllCircularUseCase.OutputValues expected = outputBuilder
                .withCircular(Collections.emptyList())
                .build();

        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    void readAllNonEmptyRepository(@Random(size = 5, type = Circular.class) List<Circular> toRead) {
        // Background
        doReturn(toRead).when(repository).readAll();

        // Given
        ReadAllCircularUseCase.InputValues input = inputBuilder.build();

        // When
        ReadAllCircularUseCase.OutputValues actual = cut.execute(input);

        // Then
        verify(repository, times(1)).readAll();

        ReadAllCircularUseCase.OutputValues expected = outputBuilder
                .withCircular(toRead)
                .build();

        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Nested
    class Input {
        @Test
        void nullObject() {
            // Given

            // When
            ReadAllCircularUseCase.InputValues actual = inputBuilder.build();

            // Then
            assertThat(actual).isNotNull();
        }
    }

    @Nested
    class Output {
        @Test
        void nullObject() {
            // Given

            // When
            ReadAllCircularUseCase.OutputValues actual = outputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(outputValues -> {
                assertThat(outputValues.getCircular()).isNull();
            });
        }

        @Test
        void fullObject(@Random(size = 5, type = Circular.class) List<Circular> read) {
            // Given
            outputBuilder
                    .withCircular(read);

            // When
            ReadAllCircularUseCase.OutputValues actual = outputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(outputValues -> assertThat(outputValues.getCircular()).isEqualTo(read));
        }
    }
}
