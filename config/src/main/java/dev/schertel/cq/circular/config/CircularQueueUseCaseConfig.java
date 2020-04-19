package dev.schertel.cq.circular.config;

import dev.schertel.cq.circular.usecase.CircularQueueUseCase;
import dev.schertel.cq.circular.usecase.input.CircularQueueDataProvider;
import dev.schertel.cq.circular.usecase.input.IdGeneratorDataProvider;
import dev.schertel.cq.circular.usecase.output.CreateCircleQueue;
import dev.schertel.cq.circular.usecase.output.DeleteCircleQueue;
import dev.schertel.cq.circular.usecase.output.ReadCircleQueue;
import dev.schertel.cq.circular.usecase.output.UpdateCircleQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircularQueueUseCaseConfig {
    @Bean
    public CreateCircleQueue createCircleQueue(CircularQueueDataProvider provider, IdGeneratorDataProvider idGenerator) {
        return new CircularQueueUseCase(provider, idGenerator);
    }

    @Bean
    public ReadCircleQueue readCircleQueue(CircularQueueDataProvider provider, IdGeneratorDataProvider idGenerator) {
        return new CircularQueueUseCase(provider, idGenerator);
    }

    @Bean
    public UpdateCircleQueue updateCircleQueue(CircularQueueDataProvider provider, IdGeneratorDataProvider idGenerator) {
        return new CircularQueueUseCase(provider, idGenerator);
    }

    @Bean
    public DeleteCircleQueue deleteCircleQueue(CircularQueueDataProvider provider, IdGeneratorDataProvider idGenerator) {
        return new CircularQueueUseCase(provider, idGenerator);
    }
}
