package com.sewah.usersservice.mappers;

import com.sewah.usersservice.dtos.UserResponseDto;
import com.sewah.usersservice.dtos.UserSignupDto;
import com.sewah.usersservice.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface UserMapper {
    UserEntity toUser(UserSignupDto userSignupDto);
    UserResponseDto toUserResponseDto(UserEntity user);

}
