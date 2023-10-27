package com.ltp.sensors.controller;

import com.ltp.sensors.model.dto.JwtResponse;
import com.ltp.sensors.model.dto.UserLoginRequest;
import com.ltp.sensors.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/auth", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JwtResponse> authenticate(@RequestBody UserLoginRequest userLoginRequest) {
        final Optional<JwtResponse> result = userService.authenticate(userLoginRequest);
        return ResponseEntity.status(result.isPresent() ? 200 : 404).body(result.orElse(null));
    }

}
