package com.mcb.billing.serviceImpl;

import com.mcb.billing.dto.UserDto;
import com.mcb.billing.ecxception.ResourceNotFoundException;
import com.mcb.billing.entity.User;
import com.mcb.billing.repository.UserRepository;
import com.mcb.billing.service.UserService;
import com.mcb.billing.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<UserDto> getAllUsers() {
       List<User> userList = userRepository.getAllUsers();
       List<UserDto> userDtoList = userList.stream()
               .map(UserConverter::convertToUserDto)
               .collect(Collectors.toList());
       return userDtoList;
    }

    @Override
    public UserDto addUser(UserDto userDto) {

        User user = UserConverter.convertToUserEntity(userDto);
        User user1 = userRepository.save(user);
        return UserConverter.convertToUserDto(user1);
    }

    @Override
    public UserDto getUserByMeterNumber(Integer number) {
        User user = userRepository.getUserByMeterNo(number);
        if (user != null)
        {
            return UserConverter.convertToUserDto(user);
        }
        else {
            throw new ResourceNotFoundException("User is not exist with given meter number : "+number);
        }
    }

    @Override
    public String deleteUserByNo(Integer number) {

        User user = userRepository.getUserByMeterNo(number);
        if (user != null)
        {
                userRepository.deleteByMeterNo(number);
                return "User Deleted Successfully!";
        }
        else
        {
            throw new ResourceNotFoundException("User is not exist with given meter number : " + number);
        }
    }

    @Override
    public UserDto updateUser(Integer number,UserDto userDto) {

        User user = userRepository.getUserByMeterNo(number);

        if (user != null)
        {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setAddress(userDto.getAddress());
            user.setCity(userDto.getCity());
            user.setState(userDto.getState());
            user.setEmail(userDto.getEmail());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setUserType(userDto.getUserType());

            User updateUser = userRepository.save(user);
            return UserConverter.convertToUserDto(updateUser);
        }
        else
        {
            throw new ResourceNotFoundException("User is not exist with given meter number : " + number);
        }


    }


}
