package com.mcb.billing.service;

import com.mcb.billing.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto addUser(UserDto userDto);

    UserDto getUserByMeterNumber(Integer number);

    String deleteUserByNo(Integer number);

    UserDto updateUser(Integer number,UserDto userDto);
}
