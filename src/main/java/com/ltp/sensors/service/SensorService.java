package com.ltp.sensors.service;

import com.ltp.sensors.model.dto.SensorsPopupDataResponse;
import com.ltp.sensors.model.dto.SensorsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorService {
    SensorsResponse findSensors(final int page);
    SensorsPopupDataResponse getPopupData();
}
