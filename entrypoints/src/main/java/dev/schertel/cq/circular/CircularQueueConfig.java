package dev.schertel.cq.circular;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircularQueueConfig {
    @Bean
    public CircularQueueDataProvider dataProvider() {
        return new CircularQueueDataProviderImp();
    }

    @Bean
    public CircularQueueUseCase useCase() {
        return new CircularQueueUseCaseImp(dataProvider());
    }

    @Bean
    public CircularQueueHandler handler() {
        return new CircularQueueHandler();
    }
}
