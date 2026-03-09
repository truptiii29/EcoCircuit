package com.chimaera.backend.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QuantumOptimizationService {

    private final RestTemplate restTemplate;
    private static final String MAXCUT_URL = "http://localhost:6000/maxcut";

    public QuantumOptimizationService() {
        this.restTemplate = new RestTemplate();
    }

    public List<String> getFarmsToIsolate(List<String> farmIds, List<Double> riskProbabilities) {
        OptimizationRequest request = new OptimizationRequest(farmIds, riskProbabilities);

        OptimizationResponse response = restTemplate.postForObject(
                MAXCUT_URL, request, OptimizationResponse.class);

        return response != null ? response.getFarmsToIsolate() : List.of();
    }

    @Data
    private static class OptimizationRequest {
        private final List<String> farmIds;
        private final List<Double> riskProbabilities;
    }

    @Data
    private static class OptimizationResponse {
        private List<String> farmsToIsolate;
    }
}
