package dev.schertel.cq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * https://medium.com/simars/inject-loggers-in-spring-java-or-kotlin-87162d02d068
 */
@Configuration
public class LoggerConfig {
    @Bean
    @Scope("prototype") // means create new instance on every injection
    public Logger logger(final InjectionPoint ip) {
        final Class lClass;
        if (null != ip.getMethodParameter()) {
            lClass = ip.getMethodParameter().getContainingClass();
        } else {
            lClass = ip.getField().getDeclaringClass();
        }
        return LoggerFactory.getLogger(lClass);
    }
}
