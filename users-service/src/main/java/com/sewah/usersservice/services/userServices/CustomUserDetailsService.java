package com.sewah.usersservice.services.userServices;

import com.sewah.usersservice.dtos.UserResponseDto;
import com.sewah.usersservice.mappers.UserMapper;
import com.sewah.usersservice.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username));

        return new User(
                userEntity.getEmail(),
                userEntity.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }

    public UserResponseDto loadUserByEmail(String username) {
        return userMapper.toUserResponseDto(userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username)));
    }
}
