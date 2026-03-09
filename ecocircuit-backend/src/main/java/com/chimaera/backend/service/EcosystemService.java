package com.chimaera.backend.service;

import com.chimaera.backend.model.EcosystemData;
import com.chimaera.backend.repository.DataRepository;
import com.chimaera.backend.util.CsvUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EcosystemService {

    private final DataRepository repository;

    public void processClimateData() {
        try (Reader reader = new BufferedReader(new InputStreamReader(
                new ClassPathResource("ecosystem_data.csv").getInputStream()))) {
            
            List<EcosystemData> data = CsvUtil.parseCsv(reader, EcosystemData.class);
            repository.saveAll(data);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EcosystemData> getClimateData() {
        return repository.findAll();
    }
}
