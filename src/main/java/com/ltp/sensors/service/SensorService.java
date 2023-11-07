package com.ltp.sensors.service;

import com.ltp.sensors.model.dto.SensorsPopupDataResponse;
import com.ltp.sensors.model.dto.SensorsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorService {
    SensorsPopupDataResponse getPopupData();
    SensorsResponse search(final String searchString, final int page);
}
