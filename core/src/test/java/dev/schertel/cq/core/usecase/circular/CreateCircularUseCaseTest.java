package dev.schertel.cq.core.usecase.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.usecase.identity.GenerateRandomIdentityUseCase;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals(id, repoCapture.getValue().getId());

        assertNotNull(actual.getCircular());

        assertAll(
                () -> assertEquals(id, actual.getCircular().getId()),
                () -> assertEquals(name, actual.getCircular().getName()),
                () -> assertEquals(description, actual.getCircular().getDescription())
        );
    }
}