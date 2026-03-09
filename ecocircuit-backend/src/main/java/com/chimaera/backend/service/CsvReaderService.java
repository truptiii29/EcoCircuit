package com.chimaera.backend.service;

import com.chimaera.backend.model.DataRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

@Service
public class CsvReaderService {

    public List<DataRecord> readCsvFile() {
        try (Reader reader = new BufferedReader(new InputStreamReader(
                new ClassPathResource("data.csv").getInputStream()))) {
            
            return new CsvToBeanBuilder<DataRecord>(reader)
                    .withType(DataRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
