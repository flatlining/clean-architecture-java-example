package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
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

    @BeforeEach
    void setUp() {
        reset(repository);
    }

    @Test
    void success(@Random Identity id, @Random String name, @Random String description) {
        // Background
        Circular circular = Circular.builder()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .build();
        doReturn(circular).when(repository).create(any(Circular.class));
        GenerateRandomIdentityUseCase.OutputValues randomIdentity = GenerateRandomIdentityUseCase.OutputValues.builder()
                .withIdentity(id)
                .build();
        doReturn(randomIdentity).when(generateRandomIdentityUseCase).execute(any(GenerateRandomIdentityUseCase.InputValues.class));

        ArgumentCaptor<Circular> repoCapture = ArgumentCaptor.forClass(Circular.class);

        // Given
        CreateCircularUseCase.InputValues input = CreateCircularUseCase.InputValues.builder()
                .withName(name)
                .withDescription(description)
                .build();

        // When
        CreateCircularUseCase.OutputValues actual = cut.execute(input);

        // Then
        verify(repository).create(repoCapture.capture());

        assertThat(repoCapture.getValue()).isNotNull().satisfies(captureParam -> {
            assertThat(captureParam.getId()).isEqualTo(id);
        });

        assertThat(actual.getCircular()).isNotNull().satisfies(act -> {
            assertThat(act.getId()).isEqualTo(id);
            assertThat(act.getName()).isEqualTo(name);
            assertThat(act.getDescription()).isEqualTo(description);
        });
    }

    @Nested
    class Input {
        CreateCircularUseCase.InputValues.Builder cut;

        @BeforeEach
        void setUp() {
            this.cut = CreateCircularUseCase.InputValues.builder();
        }

        @Test
        void nullInput() {
            // Given

            // When
            CreateCircularUseCase.InputValues actual = cut.build();

            // Then
            assertThat(actual).isNotNull().satisfies(inputValues -> {
                assertThat(inputValues.getName()).isNull();
                assertThat(inputValues.getDescription()).isNull();
            });
        }

        @Test
        void fullInput(@Random String name, @Random String description) {
            // Given
            cut
                    .withName(name)
                    .withDescription(description);

            // When
            CreateCircularUseCase.InputValues actual = cut.build();

            // Then
            assertThat(actual).isNotNull().satisfies(inputValues -> {
                assertThat(inputValues.getName()).isEqualTo(name);
                assertThat(inputValues.getDescription()).isEqualTo(description);
            });
        }
    }

    @Nested
    class Output {
        CreateCircularUseCase.OutputValues.Builder cut;

        @BeforeEach
        void setUp() {
            this.cut = CreateCircularUseCase.OutputValues.builder();
        }

        @Test
        void nullInput() {
            // Given

            // When
            CreateCircularUseCase.OutputValues actual = cut.build();

            // Then
            assertThat(actual).isNotNull().satisfies(outputValues -> {
                assertThat(outputValues.getCircular()).isNull();
            });
        }

        @Test
        void fullInput(@Random Circular circular) {
            // Given
            cut
                    .withCircular(circular);

            // When
            CreateCircularUseCase.OutputValues actual = cut.build();

            // Then
            assertThat(actual).isNotNull().satisfies(outputValues -> assertThat(outputValues.getCircular()).isEqualTo(circular));
        }
    }
}