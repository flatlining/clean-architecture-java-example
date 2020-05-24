package dev.schertel.cq.config;

import dev.schertel.cq.core.usecase.circular.CircularRepository;
import dev.schertel.cq.data.database.circular.CircularEntityRepository;
import dev.schertel.cq.data.repository.CircularRepositoryImpl;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
    @Bean
    public CircularRepository circularRepository(Logger logger, CircularEntityRepository circularEntityRepository) {
        return new CircularRepositoryImpl(logger, circularEntityRepository);
    }
}
