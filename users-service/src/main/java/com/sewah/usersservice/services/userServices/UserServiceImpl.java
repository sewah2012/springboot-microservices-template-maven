package com.sewah.usersservice.services.userServices;

import com.sewah.usersservice.dtos.UserResponseDto;
import com.sewah.usersservice.dtos.UserSignupDto;
import com.sewah.usersservice.exceptions.errors.ResourceAlreadyExistsException;
import com.sewah.usersservice.mappers.UserMapper;
import com.sewah.usersservice.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String signup(UserSignupDto request) {
        userRepo.findByEmail(request.getEmail())
                .ifPresentOrElse(
                        (x)->{ throw new ResourceAlreadyExistsException("User already exist");},
                        ()->{
                            var user = userMapper.toUser(request);
                            user.setEncryptedPassword(passwordEncoder.encode(request.getPassword()));
                            userRepo.save(user);
                        }
                );

        return "user saved successfully";
    }

    @Override
    public List<UserResponseDto> getUsers() {
        return userRepo.findAll()
                .stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }


}
