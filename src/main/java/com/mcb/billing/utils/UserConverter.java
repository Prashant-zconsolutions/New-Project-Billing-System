package com.mcb.billing.utils;

import com.mcb.billing.dto.UserDto;
import com.mcb.billing.entity.User;

public class UserConverter {

    public static UserDto convertToUserDto(User user)
    {
        return new UserDto(
                user.getMeterNumber(),
                user.getFirstName(),
                user.getLastName(),
                user.getAddress(),
                user.getCity(),
                user.getState(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getUserType()
        );
    }

    public static User convertToUserEntity(UserDto userDto)
    {
        return new User(
                userDto.getMeterNumber(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getAddress(),
                userDto.getCity(),
                userDto.getState(),
                userDto.getEmail(),
                userDto.getPhoneNumber(),
                userDto.getUserType()
        );
    }

}
