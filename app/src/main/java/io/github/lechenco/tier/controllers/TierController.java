package io.github.lechenco.tier.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.lechenco.tier.exceptions.CircuitBreakerException;
import io.github.lechenco.tier.services.CircuitBreakerService;

@RestController
@RequestMapping("/tier")
public class TierController {

    private CircuitBreakerService cbService;

    public TierController(CircuitBreakerService cbService) {
        this.cbService = cbService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @GetMapping("/circuitbreaker")
    public ResponseEntity<String> circuitbreaker() {
        try {
            return new ResponseEntity<>(cbService.execute(), HttpStatus.OK);    
        } catch (CircuitBreakerException e) {
                        return new ResponseEntity<>("Unavailable: " + e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);    

        } 
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);    
        }
        
    }
}
