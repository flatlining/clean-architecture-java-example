package dev.schertel.cq.circular.usecase;

import dev.schertel.cq.circular.usecase.input.CircularQueueDataProvider;
import dev.schertel.cq.circular.usecase.input.IdGenerator;
import dev.schertel.cq.circular.usecase.output.CreateCircleQueue;
import dev.schertel.cq.circular.usecase.output.DeleteCircleQueue;
import dev.schertel.cq.circular.usecase.output.ReadCircleQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircularQueueUseCaseConfig {
    @Bean
    public CreateCircleQueue createCircleQueue(CircularQueueDataProvider provider, IdGenerator idGenerator) {
        return new CircularQueueUseCase(provider, idGenerator);
    }

    @Bean
    public ReadCircleQueue readCircleQueue(CircularQueueDataProvider provider, IdGenerator idGenerator) {
        return new CircularQueueUseCase(provider, idGenerator);
    }

    @Bean
    public DeleteCircleQueue deleteCircleQueue(CircularQueueDataProvider provider, IdGenerator idGenerator) {
        return new CircularQueueUseCase(provider, idGenerator);
    }
}
