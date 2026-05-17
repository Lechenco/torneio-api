package io.github.lechenco.tier.infraestructure.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;

@Configuration
public class TierCircuitBreakerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(TierCircuitBreakerConfig.class);
    @Bean
    public RegistryEventConsumer<CircuitBreaker> registryEventConsumer() {
        return new RegistryEventConsumer<CircuitBreaker>() {

            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
                LOGGER.info("Circuit Breaker onEntryAddedEvent");
                entryAddedEvent.getAddedEntry().getEventPublisher()
                    .onStateTransition(event -> LOGGER.info("Circuit Breaker onStateTransition"))
                    .onError(event -> LOGGER.info("Circuit Breaker onError"))
                    .onSuccess(event -> LOGGER.info("Circuit Breaker onSuccess"))
                    .onReset(event -> LOGGER.info("Circuit Breaker onReset"))
                    .onCallNotPermitted(event -> LOGGER.info("Circuit Breaker onCallNotPermitted"))
                    ;
                
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {
                
            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {
                
            }
            
        };
    }
}
