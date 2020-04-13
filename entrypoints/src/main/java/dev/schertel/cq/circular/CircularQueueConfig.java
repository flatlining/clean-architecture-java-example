package dev.schertel.cq.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircularQueueConfig {
    @Autowired
    CircularQueueDataProviderImp circularQueueDataProvider;

    @Bean
    public ICircularQueueUseCase useCase() {
        return new CircularQueueUseCaseImp(circularQueueDataProvider);
    }
}
