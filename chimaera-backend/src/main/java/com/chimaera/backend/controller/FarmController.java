package com.chimaera.backend.controller;

import com.chimaera.backend.model.FarmData;
import com.chimaera.backend.service.ClimateService;
import com.chimaera.backend.service.QuantumOptimizationService;
import com.chimaera.backend.service.RiskPredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FarmController {

    private final ClimateService climateService;
    private final RiskPredictionService riskPredictionService;
    private final QuantumOptimizationService quantumOptimizationService;

    @GetMapping("/farms")
    public List<FarmData> getAllFarms() {
        return climateService.getAllFarms();
    }

    @GetMapping("/risk")
    public Map<String, Double> calculateRisk() {
        List<FarmData> farms = climateService.getAllFarms();
        Map<String, Double> riskMap = new HashMap<>();

        for (FarmData farm : farms) {
            Double risk = riskPredictionService.getContaminationProbability(farm);
            riskMap.put(farm.getFarmId(), risk);
        }

        return riskMap;
    }

    @GetMapping("/optimize")
    public List<String> optimizeIsolation() {
        List<FarmData> farms = climateService.getAllFarms();
        
        List<String> farmIds = farms.stream().map(FarmData::getFarmId).toList();
        List<Double> risks = farms.stream()
                .map(riskPredictionService::getContaminationProbability)
                .toList();

        return quantumOptimizationService.getFarmsToIsolate(farmIds, risks);
    }
}
