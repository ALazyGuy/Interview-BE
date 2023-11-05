package com.ltp.sensors.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SensorsResponse {
    public List<SensorDto> sensors;
    public long total;
}
