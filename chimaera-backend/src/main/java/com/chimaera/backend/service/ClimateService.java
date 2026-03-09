package com.chimaera.backend.service;

import com.chimaera.backend.model.FarmData;
import com.chimaera.backend.util.CsvFarmReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClimateService {

    private final List<FarmData> farmDataList;

    public ClimateService() {
        this.farmDataList = new java.util.ArrayList<>(CsvFarmReader.readFarmsCsv());
    }

    public void addFarm(FarmData farm) {
        this.farmDataList.add(farm);
    }

    public List<FarmData> getAllFarms() {
        return farmDataList;
    }

    public FarmData getFarmById(String id) {
        return farmDataList.stream()
                .filter(farm -> id.equals(farm.getFarmId()))
                .findFirst()
                .orElse(null);
    }
}
