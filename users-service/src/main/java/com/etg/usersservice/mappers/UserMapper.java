package com.etg.usersservice.mappers;

import com.etg.usersservice.dtos.UserResponseDto;
import com.etg.usersservice.dtos.UserSignupDto;
import com.etg.usersservice.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface UserMapper {
    UserEntity toUser(UserSignupDto userSignupDto);
    UserResponseDto toUserResponseDto(UserEntity user);

}
