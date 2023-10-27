package com.ltp.sensors.controller;

import com.ltp.sensors.model.dto.UserLoginRequest;
import com.ltp.sensors.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(@RequestBody UserLoginRequest userLoginRequest) {
        final boolean result = userService.authenticate(userLoginRequest);
        return ResponseEntity.status(result ? 200 : 404).build();
    }

}
