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
class ReadCircularUseCaseTest {

    @Mock
    CircularRepository repository;

    @InjectMocks
    ReadCircularUseCase cut;

    ReadCircularUseCase.InputValues.InputValuesBuilder inputBuilder;
    ReadCircularUseCase.OutputValues.OutputValuesBuilder outputBuilder;

    @BeforeEach
    void setUp() {
        inputBuilder = ReadCircularUseCase.InputValues.builder();
        outputBuilder = ReadCircularUseCase.OutputValues.builder();
    }

    @Test
    void read(@Random Circular read) {
        // Background
        when(repository.readByIdentity(read.getId())).thenReturn(Optional.of(read));

        // Given
        ReadCircularUseCase.InputValues input = inputBuilder
                .withIdentity(read.getId())
                .build();

        // When
        ReadCircularUseCase.OutputValues actual = cut.execute(input);

        // Then
        verify(repository, times(1)).readByIdentity(read.getId());

        ReadCircularUseCase.OutputValues expected = outputBuilder.withCircular(read).build();

        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    void failToRead(@Random Identity toRead) {
        // Background

        // Given
        ReadCircularUseCase.InputValues input = inputBuilder
                .withIdentity(toRead)
                .build();

        // When
        NotFoundException actual = assertThrows(NotFoundException.class, () -> {
            cut.execute(input);
        });

        // Then
        verify(repository, times(1)).readByIdentity(toRead);

        NotFoundException expected = NotFoundException.of(toRead.getId());

        assertThat(actual).isNotNull().isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Nested
    class Input {
        @Test
        void nullObject() {
            // Given

            // When
            ReadCircularUseCase.InputValues actual = inputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(inputValues -> {
                assertThat(inputValues.getIdentity()).isNull();
            });
        }

        @Test
        void fullObject(@Random Identity toDelete) {
            // Given
            inputBuilder
                    .withIdentity(toDelete);

            // When
            ReadCircularUseCase.InputValues actual = inputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(inputValues -> {
                assertThat(inputValues.getIdentity()).isEqualTo(toDelete);
            });
        }
    }

    @Nested
    class Output {
        @Test
        void nullObject() {
            // Given

            // When
            ReadCircularUseCase.OutputValues actual = outputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(outputValues -> {
                assertThat(outputValues.getCircular()).isNull();
            });
        }

        @Test
        void fullObject(@Random Circular created) {
            // Given
            outputBuilder
                    .withCircular(created);

            // When
            ReadCircularUseCase.OutputValues actual = outputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(outputValues -> assertThat(outputValues.getCircular()).isEqualTo(created));
        }
    }
}
