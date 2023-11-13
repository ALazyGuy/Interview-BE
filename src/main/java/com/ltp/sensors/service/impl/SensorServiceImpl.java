package com.ltp.sensors.service.impl;

import com.ltp.sensors.exception.SensorConflictException;
import com.ltp.sensors.model.dto.SensorDto;
import com.ltp.sensors.model.dto.SensorUpdateRequest;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;
    private final SensorMetricsRepository sensorMetricsRepository;
    private final SensorTypeRepository sensorTypeRepository;

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

    @Override
    public SensorsResponse search(final String searchString, final int page) {
        final Pageable pageable = PageRequest.of(page, 4);
        final Page<SensorEntity> all = sensorRepository.findAll(
                searchString.replaceAll("_", "@_"),
                pageable
        );
        final List<SensorDto> sensorDtos = all.getContent().stream()
                .map(sensorMapper::toSensorDto)
                .collect(Collectors.toList());
        final SensorsResponse sensorsResponse = new SensorsResponse(sensorDtos, all.getTotalElements());
        return sensorsResponse;
    }

    @Override
    public void deleteSensor(final long id) {
        sensorRepository.deleteById(id);
    }

    @Override
    public Optional<SensorDto> updateSensor(final long id,
                                  final SensorUpdateRequest sensorUpdateRequest) {
        final Optional<SensorEntity> optionalSensorEntity = sensorRepository.findById(id);
        if(optionalSensorEntity.isEmpty()) {
            return Optional.empty();
        }

        final SensorEntity sensorEntity = optionalSensorEntity.get();

        if(!sensorEntity.getName().equals(sensorUpdateRequest.getName())
                && sensorRepository.existsByName(sensorUpdateRequest.getName())) {
            throw new SensorConflictException("Sensor with same name already exists");
        }

        sensorEntity.setName(sensorUpdateRequest.getName());
        sensorEntity.setModel(sensorUpdateRequest.getModel());
        sensorEntity.setFrom(sensorUpdateRequest.getFrom());
        sensorEntity.setTo(sensorUpdateRequest.getTo());
        sensorEntity.setLocation(sensorUpdateRequest.getLocation());
        sensorEntity.setDescription(sensorUpdateRequest.getDescription());

        if(sensorUpdateRequest.getType() != null) {
            final Optional<SensorTypeEntity> optionalSensorTypeEntity =
                    sensorTypeRepository.findByName(sensorUpdateRequest.getType());

            if(optionalSensorEntity.isPresent()) {
                sensorEntity.setType(optionalSensorTypeEntity.get());
            }
        }

        if(sensorUpdateRequest.getUnit() != null) {
            final Optional<SensorMetricsEntity> optionalSensorMetricsEntity =
                    sensorMetricsRepository.findByValue(sensorUpdateRequest.getUnit());

            if(optionalSensorMetricsEntity.isPresent()) {
                sensorEntity.setUnit(optionalSensorMetricsEntity.get());
            }
        }

        sensorRepository.save(sensorEntity);
        return Optional.of(sensorMapper.toSensorDto(sensorEntity));
    }

    @Override
    public Optional<SensorDto> createSensor(SensorUpdateRequest sensorUpdateRequest) {
        if(sensorRepository.existsByName(sensorUpdateRequest.getName())) {
            throw new SensorConflictException("Sensor with same name already exists");
        }

        final Optional<SensorTypeEntity> optionalSensorTypeEntity =
                sensorTypeRepository.findByName(sensorUpdateRequest.getType());

        if(optionalSensorTypeEntity.isEmpty()) {
            throw new SensorConflictException("Unknown sensor type");
        }

        final Optional<SensorMetricsEntity> optionalSensorMetricsEntity =
                sensorMetricsRepository.findByValue(sensorUpdateRequest.getUnit());

        if(optionalSensorMetricsEntity.isEmpty()) {
            throw new SensorConflictException("Unknown sensor unit");
        }

        final SensorEntity sensorEntity = SensorEntity.builder()
                .name(sensorUpdateRequest.getName())
                .model(sensorUpdateRequest.getModel())
                .from(sensorUpdateRequest.getFrom())
                .to(sensorUpdateRequest.getTo())
                .location(sensorUpdateRequest.getLocation())
                .description(sensorUpdateRequest.getDescription())
                .type(optionalSensorTypeEntity.get())
                .unit(optionalSensorMetricsEntity.get())
                .build();

        sensorRepository.save(sensorEntity);
        return Optional.of(sensorMapper.toSensorDto(sensorEntity));
    }
}
