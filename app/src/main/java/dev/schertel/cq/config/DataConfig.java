package dev.schertel.cq.config;

import dev.schertel.cq.core.usecase.circular.CircularRepository;
import dev.schertel.cq.data.repository.CircularRepositoryImpl;
import dev.schertel.cq.data.database.circular.CircularEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
    @Bean
    public CircularRepository circularRepository(CircularEntityRepository circularEntityRepository) {
        return new CircularRepositoryImpl(circularEntityRepository);
    }
}
