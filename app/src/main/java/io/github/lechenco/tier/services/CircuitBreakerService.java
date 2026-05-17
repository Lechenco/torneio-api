package io.github.lechenco.tier.services;

import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import io.github.lechenco.tier.exceptions.CircuitBreakerException;
import io.github.lechenco.tier.exceptions.OpenCircuitBreakerException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class CircuitBreakerService {
    
    @CircuitBreaker(name = "tierCircuitBreaker", fallbackMethod = "executeFallback")
    public String execute() throws Exception {
        throw new OpenCircuitBreakerException("Service Error");
    }
    
    private String executeFallback(CallNotPermittedException ex) throws CircuitBreakerException {
        throw new CircuitBreakerException("Circuit Breaker active: " + ex.getMessage());
    }
}
