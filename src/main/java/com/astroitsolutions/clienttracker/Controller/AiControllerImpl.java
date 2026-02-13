package com.astroitsolutions.clienttracker.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astroitsolutions.clienttracker.Service.AiService;

import lombok.NonNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/clienttracker/analytics")
@ControllerAdvice
public class AiControllerImpl implements AiController {
    
    private AiService aiService;

    public AiControllerImpl(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/predictRevenue")
    public ResponseEntity<String> predictRevenue(@RequestParam @NonNull Integer years) {
        String prediction = aiService.predictRevenueBasedOnYears(years);
        return ResponseEntity.ok(prediction);
    }
}
