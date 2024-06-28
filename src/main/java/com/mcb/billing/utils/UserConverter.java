package com.mcb.billing.utils;

import com.mcb.billing.dto.UserDto;
import com.mcb.billing.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserConverter {


//    private static ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private static ModelMapper modelMapper;


    public static UserDto convertToUserDto(User user)
    {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;

//        return new UserDto(
//                user.getMeterNumber(),
//                user.getFirstName(),
//                user.getLastName(),
//                user.getAddress(),
//                user.getCity(),
//                user.getState(),
//                user.getEmail(),
//                user.getPhoneNumber(),
//                user.getUserType()
//        );
    }

    public static User convertToUserEntity(UserDto userDto)
    {
        User user = modelMapper.map(userDto, User.class);
        return user;

//        return new User(
//                userDto.getMeterNumber(),
//                userDto.getFirstName(),
//                userDto.getLastName(),
//                userDto.getAddress(),
//                userDto.getCity(),
//                userDto.getState(),
//                userDto.getEmail(),
//                userDto.getPhoneNumber(),
//                userDto.getUserType()
//        );
    }

}
