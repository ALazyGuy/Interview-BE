package com.ltp.sensors.service.impl;

import com.ltp.sensors.model.dto.SensorDto;
import com.ltp.sensors.model.dto.SensorsPopupDataResponse;
import com.ltp.sensors.model.dto.SensorsResponse;
import com.ltp.sensors.model.entity.SensorEntity;
import com.ltp.sensors.model.entity.SensorMetricsEntity;
import com.ltp.sensors.model.entity.SensorTypeEntity;
import com.ltp.sensors.model.mapper.SensorMapper;
import com.ltp.sensors.repository.SensorMetricsRepository;
import com.ltp.sensors.repository.SensorRepository;
import com.ltp.sensors.repository.SensorTypeRepository;
import com.ltp.sensors.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;
    private final SensorMetricsRepository sensorMetricsRepository;
    private final SensorTypeRepository sensorTypeRepository;

    @Override
    public SensorsResponse findSensors(final int page) {
        final Pageable pageable = PageRequest.of(page, 4);
        final Page<SensorEntity> all = sensorRepository.findAll(pageable);
        final List<SensorDto> sensorDtos = all.getContent().stream()
                .map(sensorMapper::toSensorDto)
                .collect(Collectors.toList());
        final SensorsResponse sensorsResponse = new SensorsResponse(sensorDtos, all.getTotalElements());
        return sensorsResponse;
    }

    @Override
    public SensorsPopupDataResponse getPopupData() {
        final List<String> units = sensorMetricsRepository.findAll().stream()
                .map(SensorMetricsEntity::getValue)
                .collect(Collectors.toList());
        final List<String> types = sensorTypeRepository.findAll().stream()
                .map(SensorTypeEntity::getName)
                .collect(Collectors.toList());
        final SensorsPopupDataResponse response = SensorsPopupDataResponse.builder()
                .units(units)
                .types(types)
                .build();
        return response;
    }
}
