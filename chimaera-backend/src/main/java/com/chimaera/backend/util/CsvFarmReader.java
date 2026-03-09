package com.chimaera.backend.util;

import com.chimaera.backend.model.FarmData;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

public class CsvFarmReader {

    private CsvFarmReader() {
    }

    public static List<FarmData> readFarmsCsv() {
        try (Reader reader = new BufferedReader(new InputStreamReader(
                new ClassPathResource("data/farms.csv").getInputStream()))) {
            
            return new CsvToBeanBuilder<FarmData>(reader)
                    .withType(FarmData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
