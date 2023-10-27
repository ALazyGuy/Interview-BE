package com.ltp.sensors.service.impl;

import com.ltp.sensors.model.dto.UserLoginRequest;
import com.ltp.sensors.model.entity.UserEntity;
import com.ltp.sensors.repository.UserRepository;
import com.ltp.sensors.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean authenticate(final UserLoginRequest userLoginRequest) {
        final Optional<UserEntity> userEntity = userRepository.findByLogin(userLoginRequest.getLogin());
        return userEntity.isPresent() &&
                passwordEncoder.matches(userLoginRequest.getPassword(), userEntity.get().getPasswordHash());
    }
}
