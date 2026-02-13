package com.astroitsolutions.clienttracker.External.analytics;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AiOrchestrationAdaptor {

    private RestClient restClient;

    @Value("${ai.orchestration.base.url}")
    private String AI_ORCHESTRATION_BASE_URL;

    public AiOrchestrationAdaptor(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }
    
    
    public String aiOrchestrationGetPredictionsByYears(int years) {

        URI uri = UriComponentsBuilder.fromUriString(AI_ORCHESTRATION_BASE_URL + "/aiorchestration/predictions")
            .queryParam("years", String.valueOf(years))
            .build(true) 
            .toUri();

        ResponseEntity<String> response = restClient.get()
        .uri(uri)
        .retrieve()
        .toEntity(String.class);

        return response.getBody();

    }
}
