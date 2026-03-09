package com.chimaera.backend.controller;

import com.chimaera.backend.model.DataRecord;
import com.chimaera.backend.service.CsvReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.GetMapping;
import org.springframework.web.bind.RequestMapping;
import org.springframework.web.bind.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class DataController {

    private final CsvReaderService csvReaderService;

    @GetMapping
    public List<DataRecord> getAllData() {
        return csvReaderService.readCsvFile();
    }
}
