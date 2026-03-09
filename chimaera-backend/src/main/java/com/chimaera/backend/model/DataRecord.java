package com.chimaera.backend.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class DataRecord {

    @CsvBindByName(column = "id")
    private Long id;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "value")
    private Integer value;
}
