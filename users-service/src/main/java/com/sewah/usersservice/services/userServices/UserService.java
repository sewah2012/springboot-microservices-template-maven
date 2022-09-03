package com.sewah.usersservice.services.userServices;

import com.sewah.usersservice.dtos.UserResponseDto;
import com.sewah.usersservice.dtos.UserSignupDto;

import java.util.List;

public interface UserService{
    String signup(UserSignupDto request);

    List<UserResponseDto> getUsers();

}
