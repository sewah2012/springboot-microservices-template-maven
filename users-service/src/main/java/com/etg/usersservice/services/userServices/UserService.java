package com.etg.usersservice.services.userServices;

import com.etg.usersservice.dtos.UserResponseDto;
import com.etg.usersservice.dtos.UserSignupDto;

import java.util.List;

public interface UserService{
    String signup(UserSignupDto request);

    List<UserResponseDto> getUsers();

}
