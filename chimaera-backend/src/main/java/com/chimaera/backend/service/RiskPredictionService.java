package com.chimaera.backend.service;

import com.chimaera.backend.model.FarmData;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RiskPredictionService {

    private final RestTemplate restTemplate;
    private static final String PREDICT_URL = "http://localhost:5000/predict";

    public RiskPredictionService() {
        this.restTemplate = new RestTemplate();
    }

    public Double getContaminationProbability(FarmData farmData) {
        PredictionRequest request = new PredictionRequest(
                farmData.getTemperature(),
                farmData.getHumidity(),
                farmData.getWindSpeed()
        );

        PredictionResponse response = restTemplate.postForObject(
                PREDICT_URL, request, PredictionResponse.class);

        return response != null ? response.getContaminationProbability() : null;
    }

    @Data
    private static class PredictionRequest {
        private final Double temperature;
        private final Double humidity;
        private final Double windSpeed;
    }

    @Data
    private static class PredictionResponse {
        private Double contaminationProbability;
    }
}
