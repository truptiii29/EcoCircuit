package com.chimaera.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chimaera.backend.model.DataRecord;
import com.chimaera.backend.service.CsvReaderService;
import lombok.RequiredArgsConstructor;

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
