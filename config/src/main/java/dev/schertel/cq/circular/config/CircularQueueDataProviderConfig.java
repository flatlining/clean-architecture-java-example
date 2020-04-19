package dev.schertel.cq.circular.config;

import dev.schertel.cq.circular.dataprovider.InMemoryCircularQueueDatabase;
import dev.schertel.cq.circular.usecase.input.CircularQueueDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircularQueueDataProviderConfig {
    @Bean
    public CircularQueueDataProvider circularQueueDataProvider() {
        return new InMemoryCircularQueueDatabase();
    }
}
