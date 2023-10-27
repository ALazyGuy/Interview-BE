package com.ltp.sensors.service;

import com.ltp.sensors.model.dto.UserLoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean authenticate(UserLoginRequest userLoginRequest);
}
