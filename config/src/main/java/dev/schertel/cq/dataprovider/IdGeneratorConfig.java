package dev.schertel.cq.dataprovider;

import dev.schertel.cq.circular.usecase.input.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdGeneratorConfig {
    @Bean
    public IdGenerator idGenerator() {
        return new UUIDGenerator();
    }
}
