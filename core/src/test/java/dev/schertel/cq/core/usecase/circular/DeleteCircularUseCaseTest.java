package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    void successDeleteExisting(@Random Circular deleted) {
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
}