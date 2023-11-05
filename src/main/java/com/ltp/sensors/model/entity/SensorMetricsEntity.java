package com.ltp.sensors.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sensor_metrics")
public class SensorMetricsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
}
