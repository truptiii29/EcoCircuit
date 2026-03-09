package com.chimaera.backend.repository;

import com.chimaera.backend.model.EcosystemData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataRepository {
    
    private final List<EcosystemData> dataStore = new ArrayList<>();

    public void saveAll(List<EcosystemData> data) {
        dataStore.clear();
        dataStore.addAll(data);
    }

    public List<EcosystemData> findAll() {
        return new ArrayList<>(dataStore);
    }
}
