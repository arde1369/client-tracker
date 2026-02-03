package com.astroitsolutions.clienttracker.Configuration.CircuitBreakers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;

@Configuration
public class DatabaseCircuitBreaker {

    @Value("${circuitbreaker.database.failureRateThreshold:20}")
    private int failureRateThreshold;

    @Value("${circuitbreaker.database.slowCallRateThreshold:20}")
    private int slowCallRateThreshold; 

    @Value("${circuitbreaker.database.waitDurationInOpenState:15}")
    private int waitDurationInOpenState;

    @Value("${circuitbreaker.database.slowCallDurationThreshold:15}")
    private int slowCallDurationThreshold;

    @Value("${circuitbreaker.database.permittedNumberOfCallsInHalfOpenState:6}")
    private int permittedNumberOfCallsInHalfOpenState;

    @Value("${circuitbreaker.database.minimumNumberOfCalls:20}")
    private int minimumNumberOfCalls;

    @Value("${circuitbreaker.database.slidingWindowSize:30}")
    private int slidingWindowSize;

    private final String CIRCUIT_BREAKER_NAME = "databaseCircuitBreaker";

    @Value("${circuitbreaker.allBreakersOff:false}")
    private boolean allBreakersOff;

    @Value("${circuitbreaker.database.breakerOff:false}")
    private boolean databaseBreakerOff;


    @Bean
    public CircuitBreakerConfig databaseCircuitBreakerConfig() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
            .failureRateThreshold(failureRateThreshold)
            .slowCallRateThreshold(slowCallRateThreshold)
            .waitDurationInOpenState(Duration.ofSeconds(waitDurationInOpenState))
            .slowCallDurationThreshold(Duration.ofSeconds(slowCallDurationThreshold))
            .permittedNumberOfCallsInHalfOpenState(permittedNumberOfCallsInHalfOpenState)
            .minimumNumberOfCalls(minimumNumberOfCalls)
            .slidingWindowType(SlidingWindowType.TIME_BASED)
            .slidingWindowSize(slidingWindowSize)
            .build();
		return circuitBreakerConfig;
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry(CircuitBreakerConfig circuitBreakerConfig) {
        return CircuitBreakerRegistry.of(circuitBreakerConfig);
    }


    @Bean
    public CircuitBreaker databaseCircuitBreakerBean(CircuitBreakerRegistry circuitBreakerRegistry, CircuitBreakerConfig circuitBreakerConfig) {
        CircuitBreaker databaseCircuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME, circuitBreakerConfig);
        if(allBreakersOff || databaseBreakerOff){
            databaseCircuitBreaker.transitionToClosedState();
        }
        return databaseCircuitBreaker;
    }
}