package dev.schertel.cq.circular.config;

import dev.schertel.cq.circular.dataprovider.UUIDGenerator;
import dev.schertel.cq.circular.usecase.input.IdGeneratorDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdGeneratorDataProviderConfig {
    @Bean
    public IdGeneratorDataProvider idGeneratorDataProvider() {
        return new UUIDGenerator();
    }
}
