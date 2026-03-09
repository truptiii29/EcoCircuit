package com.chimaera.backend.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class FarmData {

    @CsvBindByName(column = "farm_id")
    private String farmId;

    @CsvBindByName(column = "temperature")
    private Double temperature;

    @CsvBindByName(column = "humidity")
    private Double humidity;

    @CsvBindByName(column = "wind_speed")
    private Double windSpeed;

    @CsvBindByName(column = "latitude")
    private Double latitude;

    @CsvBindByName(column = "longitude")
    private Double longitude;
}
