package com.ltp.sensors.model.dto;

import lombok.Data;

@Data
public class SensorUpdateRequest {
    private String name;
    private String model;
    private String type;
    private String unit;
    private String location;
    private String description;
    private int from;
    private int to;
}
