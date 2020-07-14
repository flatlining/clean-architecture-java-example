package dev.schertel.cq.core.usecase.identity;

import dev.schertel.cq.core.domain.Identity;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(RandomBeansExtension.class)
class GenerateRandomIdentityUseCaseTest {

    @InjectMocks
    GenerateRandomIdentityUseCase cut;

    @Test
    void execute() {
        // Background

        // Given
        GenerateRandomIdentityUseCase.InputValues input = GenerateRandomIdentityUseCase.InputValues.builder().build();

        // When
        GenerateRandomIdentityUseCase.OutputValues actual = cut.execute(input);

        // Then
        assertThat(actual.getIdentity()).isNotNull().satisfies(identity -> {
            assertThat(identity.getId())
                    .isNotNull()
                    .isEqualTo(UUID.fromString(identity.getId()).toString());
        });
    }

    @Nested
    class Input {
        GenerateRandomIdentityUseCase.InputValues.Builder cut;

        @BeforeEach
        void setUp() {
            this.cut = GenerateRandomIdentityUseCase.InputValues.builder();
        }

        @Test
        void nullFullInput() {
            // Given

            // When
            GenerateRandomIdentityUseCase.InputValues actual = cut.build();

            // Then
            assertThat(actual).isNotNull();
        }
    }

    @Nested
    class Output {
        GenerateRandomIdentityUseCase.OutputValues.Builder cut;

        @BeforeEach
        void setUp() {
            this.cut = GenerateRandomIdentityUseCase.OutputValues.builder();
        }

        @Test
        void nullInput() {
            // Given

            // When
            GenerateRandomIdentityUseCase.OutputValues actual = cut.build();

            // Then
            assertThat(actual).isNotNull().satisfies(outputValues -> {
                assertThat(outputValues.getIdentity()).isNull();
            });
        }

        @Test
        void fullInput(@Random Identity identity) {
            // Given
            cut
                    .withIdentity(identity);

            // When
            GenerateRandomIdentityUseCase.OutputValues actual = cut.build();

            // Then
            assertThat(actual).isNotNull().satisfies(outputValues -> assertThat(outputValues.getIdentity()).isEqualTo(identity));
        }
    }
}
