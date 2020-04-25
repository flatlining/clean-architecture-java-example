package dev.schertel.cq.circular.config;

import dev.schertel.cq.circular.usecase.CircularQueueUseCase;
import dev.schertel.cq.circular.usecase.input.CircularQueueDataProvider;
import dev.schertel.cq.usecase.output.GenerateId;
import dev.schertel.cq.circular.usecase.output.CreateCircleQueue;
import dev.schertel.cq.circular.usecase.output.DeleteCircleQueue;
import dev.schertel.cq.circular.usecase.output.ReadCircleQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircularQueueUseCaseConfig {
    @Bean
    public CreateCircleQueue createCircleQueue(CircularQueueDataProvider provider, GenerateId idGenerator) {
        return new CircularQueueUseCase(provider, idGenerator);
    }

    @Bean
    public ReadCircleQueue readCircleQueue(CircularQueueDataProvider provider, GenerateId idGenerator) {
        return new CircularQueueUseCase(provider, idGenerator);
    }

    @Bean
    public DeleteCircleQueue deleteCircleQueue(CircularQueueDataProvider provider, GenerateId idGenerator) {
        return new CircularQueueUseCase(provider, idGenerator);
    }
}
