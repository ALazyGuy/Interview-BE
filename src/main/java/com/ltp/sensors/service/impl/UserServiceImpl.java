package com.ltp.sensors.service.impl;

import com.ltp.sensors.model.dto.JwtResponse;
import com.ltp.sensors.model.dto.RoleResponse;
import com.ltp.sensors.model.dto.UserLoginRequest;
import com.ltp.sensors.model.entity.UserEntity;
import com.ltp.sensors.repository.UserRepository;
import com.ltp.sensors.security.JwtUtils;
import com.ltp.sensors.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public Optional<JwtResponse> authenticate(final UserLoginRequest userLoginRequest) {
        final Optional<UserEntity> userEntity = userRepository.findByLogin(userLoginRequest.getLogin());

        if (userEntity.isEmpty()) {
            return Optional.empty();
        }

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), userEntity.get().getPasswordHash())) {
            return Optional.empty();
        }

        final String token = jwtUtils.generate(userLoginRequest.getLogin());
        final JwtResponse response = new JwtResponse(String.format("Bearer %s", token));
        return Optional.of(response);
    }

    @Override
    public RoleResponse getRole() {
        final String login = SecurityContextHolder.getContext().getAuthentication().getName();
        final Optional<UserEntity> userEntity = userRepository.findByLogin(login);
        if(userEntity.isEmpty()) {
            return new RoleResponse("");
        }

        return new RoleResponse(userEntity.get().getRole().name());
    }
}
