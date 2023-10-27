package com.ltp.sensors.service.impl;

import com.ltp.sensors.model.entity.UserDetailsImpl;
import com.ltp.sensors.model.entity.UserEntity;
import com.ltp.sensors.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final Optional<UserEntity> userEntity = userRepository.findByLogin(login);
        return new UserDetailsImpl(userEntity.orElseThrow(() -> new UsernameNotFoundException(login)));
    }
}
