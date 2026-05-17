package io.github.lechenco.tier.exceptions;

public class OpenCircuitBreakerException extends Exception{

    public OpenCircuitBreakerException(String message) {
        super(message);
    }
}
