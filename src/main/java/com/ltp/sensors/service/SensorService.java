package com.ltp.sensors.service;

import com.ltp.sensors.model.dto.SensorDto;
import com.ltp.sensors.model.dto.SensorUpdateRequest;
import com.ltp.sensors.model.dto.SensorsPopupDataResponse;
import com.ltp.sensors.model.dto.SensorsResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SensorService {
    SensorsPopupDataResponse getPopupData();
    SensorsResponse search(final String searchString, final int page);
    void deleteSensor(final long id);
    Optional<SensorDto> updateSensor(final long id, final SensorUpdateRequest sensorUpdateRequest);
    Optional<SensorDto> createSensor(final SensorUpdateRequest sensorUpdateRequest);
}
