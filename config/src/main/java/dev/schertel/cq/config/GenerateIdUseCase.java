package dev.schertel.cq.config;

import dev.schertel.cq.usecase.UUIDUseCase;
import dev.schertel.cq.usecase.output.GenerateId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenerateIdUseCase {
    @Bean
    public GenerateId generateId() {
        return new UUIDUseCase();
    }
}
