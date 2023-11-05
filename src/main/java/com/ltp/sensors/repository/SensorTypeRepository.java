package com.ltp.sensors.repository;

import com.ltp.sensors.model.entity.SensorTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorTypeRepository extends JpaRepository<SensorTypeEntity, Long> {
}
