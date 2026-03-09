package com.chimaera.backend.controller;

import com.chimaera.backend.model.EcosystemData;
import com.chimaera.backend.service.EcosystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ecosystem")
@RequiredArgsConstructor
public class EcosystemController {

    private final EcosystemService service;

    @PostMapping("/process")
    public void processData() {
        service.processClimateData();
    }

    @GetMapping
    public List<EcosystemData> getData() {
        return service.getClimateData();
    }
}
