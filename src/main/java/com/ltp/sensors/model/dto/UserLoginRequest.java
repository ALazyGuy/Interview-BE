package com.ltp.sensors.model.dto;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String login;
    private String password;
}
