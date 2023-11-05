package com.ltp.sensors.model.mapper;

import com.ltp.sensors.model.dto.SensorDto;
import com.ltp.sensors.model.entity.SensorEntity;
import org.springframework.stereotype.Component;

@Component
public class SensorMapper {

    public SensorDto toSensorDto(final SensorEntity sensorEntity) {
        return SensorDto.builder()
                .id(sensorEntity.getId())
                .name(sensorEntity.getName())
                .model(sensorEntity.getModel())
                .from(sensorEntity.getFrom())
                .to(sensorEntity.getTo())
                .location(sensorEntity.getLocation())
                .description(sensorEntity.getDescription())
                .type(sensorEntity.getType().getName())
                .unit(sensorEntity.getUnit().getValue())
                .build();
    }

}
