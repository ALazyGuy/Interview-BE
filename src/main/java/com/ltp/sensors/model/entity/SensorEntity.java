package com.ltp.sensors.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sensors")
public class SensorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String model;
    @Column(name = "`from`")
    private int from;
    @Column(name = "`to`")
    private int to;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_type_id", referencedColumnName = "id")
    private SensorTypeEntity type;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_metric_id", referencedColumnName = "id")
    private SensorMetricsEntity unit;
    private String location;
    private String description;
}
