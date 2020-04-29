package dev.schertel.cq.config;

import dev.schertel.cq.core.usecase.circular.*;
import dev.schertel.cq.core.usecase.identity.GenerateRandomIdentityUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {
    @Bean
    public GenerateRandomIdentityUseCase generateRandomIdentityUseCase() {
        return new GenerateRandomIdentityUseCase();
    }

    @Bean
    public CreateCircularUseCase createCircularUseCase(CircularRepository repository, GenerateRandomIdentityUseCase generateRandomIdentityUseCase) {
        return new CreateCircularUseCase(repository, generateRandomIdentityUseCase);
    }

    @Bean
    public ReadAllCircularUseCase readAllCircularUseCase(CircularRepository repository) {
        return new ReadAllCircularUseCase(repository);
    }

    @Bean
    public ReadCircularUseCase readCircularUseCase(CircularRepository repository) {
        return new ReadCircularUseCase(repository);
    }

    @Bean
    public DeleteAllCircularUseCase deleteAllCircularUseCase(CircularRepository repository) {
        return new DeleteAllCircularUseCase(repository);
    }

    @Bean
    DeleteCircularUseCase deleteCircularUseCase(CircularRepository repository) {
        return new DeleteCircularUseCase(repository);
    }
}
