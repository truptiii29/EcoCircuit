package com.chimaera.backend.controller;

import com.chimaera.backend.model.FarmData;
import com.chimaera.backend.service.ClimateService;
import com.chimaera.backend.service.QuantumOptimizationService;
import com.chimaera.backend.service.RiskPredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/integration")
@RequiredArgsConstructor
public class IntegrationScenarioController {

    private final ClimateService climateService;
    private final RiskPredictionService riskPredictionService;
    private final QuantumOptimizationService quantumOptimizationService;

    @GetMapping("/scenario")
    public Map<String, Object> runTestScenario() {
        Map<String, Object> finalResponse = new HashMap<>();

        // 1. Load farms from CSV
        List<FarmData> farms = climateService.getAllFarms();
        finalResponse.put("totalFarmsLoaded", farms.size());

        // 2. Predict risk for each farm
        List<String> farmIds = farms.stream().map(FarmData::getFarmId).toList();
        List<Double> risks = farms.stream()
                .map(riskPredictionService::getContaminationProbability)
                .toList();

        Map<String, Double> farmRisks = new HashMap<>();
        for (int i = 0; i < farms.size(); i++) {
            farmRisks.put(farmIds.get(i), risks.get(i));
        }
        finalResponse.put("riskPredictions", farmRisks);

        // 3. Send the results to the quantum solver
        // 4. Return farms recommended for quarantine
        List<String> quarantinedFarms = quantumOptimizationService.getFarmsToIsolate(farmIds, risks);
        finalResponse.put("farmsRecommendedForQuarantine", quarantinedFarms);

        // 5. Return the final response as JSON
        return finalResponse;
    }
}
