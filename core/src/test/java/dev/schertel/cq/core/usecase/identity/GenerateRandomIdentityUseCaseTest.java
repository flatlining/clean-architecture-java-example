package dev.schertel.cq.core.usecase.identity;

import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(RandomBeansExtension.class)
class GenerateRandomIdentityUseCaseTest {

    @InjectMocks
    private GenerateRandomIdentityUseCase cut;

    @Test
    void execute() {
        // Background

        // Given
        GenerateRandomIdentityUseCase.InputValues input = GenerateRandomIdentityUseCase.InputValues.builder().build();

        // When
        GenerateRandomIdentityUseCase.OutputValues actual = cut.execute(input);

        // Then
        assertNotNull(actual.getIdentity());

        assertAll(
                () -> assertNotNull(actual.getIdentity().getId()),
                () -> assertEquals(UUID.fromString(actual.getIdentity().getId()).toString(), actual.getIdentity().getId())
        );
    }
}