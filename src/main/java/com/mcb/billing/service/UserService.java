package com.mcb.billing.service;

import com.mcb.billing.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto addUser(UserDto userDto);

    UserDto getUserByMeterNumber(Integer meterNumber);

    Boolean deleteUserByNo(Integer meterNumber);

    UserDto updateUser(Integer meterNumber,UserDto userDto);
}
