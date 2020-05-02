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

    @BeforeEach
    void setUp() {
        reset(repository);
    }

    @Test
    void successEmptyRepository() {
        // Background
        doReturn(Collections.emptyList()).when(repository).deleteAll();

        // Given
        DeleteAllCircularUseCase.InputValues input = DeleteAllCircularUseCase.InputValues.builder()
                .build();

        // When
        DeleteAllCircularUseCase.OutputValues actual = cut.execute(input);

        // Then
        verify(repository, times(1)).deleteAll();

        assertThat(actual).isNotNull();
    }

    @Test
    void successNonEmptyRepository(@Random(size = 5, type = Circular.class) List<Circular> deleted) {
        // Background
        doReturn(deleted).when(repository).deleteAll();

        // Given
        DeleteAllCircularUseCase.InputValues input = DeleteAllCircularUseCase.InputValues.builder()
                .build();

        // When
        DeleteAllCircularUseCase.OutputValues actual = cut.execute(input);

        // Then
        verify(repository, times(1)).deleteAll();

        assertThat(actual).isNotNull();
    }
}