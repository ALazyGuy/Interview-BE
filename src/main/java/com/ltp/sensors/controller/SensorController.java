package com.ltp.sensors.controller;

import com.ltp.sensors.model.dto.SensorsPopupDataResponse;
import com.ltp.sensors.model.dto.SensorsResponse;
import com.ltp.sensors.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.OPTIONS})
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

}
