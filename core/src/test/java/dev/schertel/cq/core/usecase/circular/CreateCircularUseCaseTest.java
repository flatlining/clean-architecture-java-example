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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(RandomBeansExtension.class)
class CreateCircularUseCaseTest {

    @Mock
    private CircularRepository repository;

    @Mock
    private GenerateRandomIdentityUseCase generateRandomIdentityUseCase;

    @InjectMocks
    private CreateCircularUseCase cut;

    private CreateCircularUseCase.InputValues.Builder inputBuilder;
    private CreateCircularUseCase.OutputValues.Builder outputBuilder;

    @BeforeEach
    void setUp() {
        reset(repository);
        inputBuilder = CreateCircularUseCase.InputValues.builder();
        outputBuilder = CreateCircularUseCase.OutputValues.builder();
    }

    @Test
    void success(@Random Circular created) {
        // Background
        doReturn(created).when(repository).create(any(Circular.class));
        GenerateRandomIdentityUseCase.OutputValues randomIdentity = GenerateRandomIdentityUseCase.OutputValues.builder()
                .withIdentity(created.getId())
                .build();
        doReturn(randomIdentity).when(generateRandomIdentityUseCase).execute(any(GenerateRandomIdentityUseCase.InputValues.class));

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
        void nullInput() {
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
        void fullInput(@Random String name, @Random String description) {
            // Given
            inputBuilder
                    .withName(name)
                    .withDescription(description);

            // When
            CreateCircularUseCase.InputValues actual = inputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(inputValues -> {
                assertThat(inputValues.getName()).isEqualTo(name);
                assertThat(inputValues.getDescription()).isEqualTo(description);
            });
        }
    }

    @Nested
    class Output {
        @Test
        void nullInput() {
            // Given

            // When
            CreateCircularUseCase.OutputValues actual = outputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(outputValues -> {
                assertThat(outputValues.getCircular()).isNull();
            });
        }

        @Test
        void fullInput(@Random Circular circular) {
            // Given
            outputBuilder
                    .withCircular(circular);

            // When
            CreateCircularUseCase.OutputValues actual = outputBuilder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(outputValues -> assertThat(outputValues.getCircular()).isEqualTo(circular));
        }
    }
}
