package com.ltp.sensors.repository;

import com.ltp.sensors.model.entity.SensorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Long> {
    @Query("select s from SensorEntity s where s.name like %?1% escape '@'" +
                " or s.model like %?1% escape '@'" +
                " or s.location like %?1% escape '@'" +
                " or s.description like %?1% escape '@'" +
                " or s.type.name like %?1% escape '@'" +
                " or s.unit.value like %?1% escape '@' order by s.name asc")
    Page<SensorEntity> findAll(final String searchString, final Pageable pageable);
    boolean existsByName(final String name);
}
