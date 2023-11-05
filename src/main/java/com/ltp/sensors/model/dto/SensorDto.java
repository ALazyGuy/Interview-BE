package com.ltp.sensors.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorDto {
    private Long id;
    private String model;
    private String name;
    private int from;
    private int to;
    private String type;
    private String unit;
    private String location;
    private String description;
}
