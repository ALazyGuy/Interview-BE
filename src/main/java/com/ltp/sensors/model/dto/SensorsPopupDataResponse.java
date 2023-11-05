package com.ltp.sensors.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SensorsPopupDataResponse {
    private List<String> units;
    private List<String> types;
}
