package com.astroitsolutions.clienttracker.Service;

import org.springframework.stereotype.Service;

import com.astroitsolutions.clienttracker.External.analytics.AiOrchestrationAdaptor;


@Service
public class AiService {

    private final AiOrchestrationAdaptor aiOrchestrationAdaptor;

    public AiService(AiOrchestrationAdaptor aiOrchestrationAdaptor) {
        this.aiOrchestrationAdaptor = aiOrchestrationAdaptor;
    }

    public String predictRevenueBasedOnYears(int years) {
        return aiOrchestrationAdaptor.aiOrchestrationGetPredictionsByYears(years);
    }
    
}
