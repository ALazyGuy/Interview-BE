package com.ltp.sensors.controller;

import com.ltp.sensors.model.dto.SensorDto;
import com.ltp.sensors.model.dto.SensorUpdateRequest;
import com.ltp.sensors.model.dto.SensorsPopupDataResponse;
import com.ltp.sensors.model.dto.SensorsResponse;
import com.ltp.sensors.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST})
@RequestMapping("/api/sensor")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @GetMapping("/popup")
    public ResponseEntity<SensorsPopupDataResponse> getPopupData(HttpServletRequest request) {
        if(!request.isUserInRole("ADMINISTRATOR")) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(sensorService.getPopupData());
    }

    @GetMapping("/search")
    public ResponseEntity<SensorsResponse> search(@RequestParam String searchString, @RequestParam int page) {
        final SensorsResponse response = sensorService.search(searchString, page);
        return ResponseEntity.status(response.sensors.isEmpty() ? 204 : 200).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSensorById(@PathVariable long id) {
        sensorService.deleteSensor(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateSensor(@PathVariable long id,
                                       @RequestBody SensorUpdateRequest sensorUpdateRequest) {
        final Optional<SensorDto> optionalSensorDto = sensorService.updateSensor(id, sensorUpdateRequest);
        if(optionalSensorDto.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(optionalSensorDto.get());
    }

    @PostMapping("/create")
    public ResponseEntity createSensor(@RequestBody SensorUpdateRequest sensorUpdateRequest) {
        final Optional<SensorDto> optionalSensorDto = sensorService.createSensor(sensorUpdateRequest);
        if(optionalSensorDto.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(optionalSensorDto.get());
    }

}
