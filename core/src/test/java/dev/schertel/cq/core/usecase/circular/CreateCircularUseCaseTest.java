package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.usecase.identity.GenerateRandomIdentityUseCase;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(RandomBeansExtension.class)
class CreateCircularUseCaseTest {

    @Mock
    CircularRepository repository;
    @Mock
    GenerateRandomIdentityUseCase generateRandomIdentityUseCase;

    @InjectMocks
    CreateCircularUseCase cut;

    CreateCircularUseCase.InputValues.Builder inputBuilder;
    CreateCircularUseCase.OutputValues.Builder outputBuilder;

    @BeforeEach
    void setUp() {
        inputBuilder = CreateCircularUseCase.InputValues.builder();
        outputBuilder = CreateCircularUseCase.OutputValues.builder();
    }

    @Test
    void create(@Random Circular created) {
        // Background
        when(repository.create(Circular.builder()
                .withId(created.getId())
                .withName(created.getName())
                .withDescription(created.getDescription())
                .build())).thenReturn(created);

        GenerateRandomIdentityUseCase.OutputValues randomIdentity = GenerateRandomIdentityUseCase.OutputValues.builder()
                .withIdentity(created.getId())
                .build();
        when(generateRandomIdentityUseCase.execute(GenerateRandomIdentityUseCase.InputValues.builder().build()))
                .thenReturn(randomIdentity);

        ArgumentCaptor<Circular> repoCapture = ArgumentCaptor.forClass(Circular.class);

        // Given
        CreateCircularUseCase.InputValues input = inputBuilder
                .withName(created.getName())
                .withDescription(created.getDescription())
                .build();

        // When
        CreateCircularUseCase.OutputValues actual = cut.execute(input);

        // Then
        verify(repository).create(repoCapture.capture());
        assertThat(repoCapture.getValue()).isNotNull().satisfies(captureParam -> {
            assertThat(captureParam.getId()).isEqualTo(created.getId());
        });

        CreateCircularUseCase.OutputValues expected = outputBuilder
                .withCircular(created)
                .build();

        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Nested
    class Input {
        @Test
        void nullObject() {
            // Given

            // When
            CreateCircularUseCase.InputValues actual = inputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(inputValues -> {
                assertThat(inputValues.getName()).isNull();
                assertThat(inputValues.getDescription()).isNull();
            });
        }

        @Test
        void fullObject(@Random Circular toCreate) {
            // Given
            inputBuilder
                    .withName(toCreate.getName())
                    .withDescription(toCreate.getDescription());

            // When
            CreateCircularUseCase.InputValues actual = inputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(inputValues -> {
                assertThat(inputValues.getName()).isEqualTo(toCreate.getName());
                assertThat(inputValues.getDescription()).isEqualTo(toCreate.getDescription());
            });
        }
    }

    @Nested
    class Output {
        @Test
        void nullObject() {
            // Given

            // When
            CreateCircularUseCase.OutputValues actual = outputBuilder.build();

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
            CreateCircularUseCase.OutputValues actual = outputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(outputValues -> assertThat(outputValues.getCircular()).isEqualTo(created));
        }
    }
}
