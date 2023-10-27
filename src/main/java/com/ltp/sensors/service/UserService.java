package com.ltp.sensors.service;

import com.ltp.sensors.model.dto.JwtResponse;
import com.ltp.sensors.model.dto.RoleResponse;
import com.ltp.sensors.model.dto.UserLoginRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<JwtResponse> authenticate(UserLoginRequest userLoginRequest);
    RoleResponse getRole();
}
