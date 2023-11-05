package com.ltp.sensors.repository;

import com.ltp.sensors.model.entity.SensorMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorMetricsRepository extends JpaRepository<SensorMetricsEntity, Long> {
}
