package com.mcb.billing.serviceImpl;

import com.mcb.billing.dto.UserDto;
import com.mcb.billing.ecxception.ResourceNotFoundException;
import com.mcb.billing.entity.User;
import com.mcb.billing.repository.UserRepository;
import com.mcb.billing.service.UserService;
import com.mcb.billing.utils.UserConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserDto> getAllUsers() {

//        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT first_name , last_name FROM users");
//        List<String> firstName = new ArrayList<>();
//        List<String> lastName = new ArrayList<>();
//        for(Map<String,Object> row: rows)
//        {
//            firstName.add(row.get("first_name").toString());
//            lastName.add(row.get("last_name").toString());
//        }
//        firstName.forEach(s-> System.out.println(s));
//        lastName.forEach(s-> System.out.println(s));



//        result.forEach(ro->{
//            ro.entrySet()
//                    .forEach(entry-> {
//                        System.out.println(entry.getKey()+" : "+entry.getValue());
//                    });
//        });

        
       List<User> userList = userRepository.getAllUsers();
       List<UserDto> userDtoList = userList.stream()
               .map(list -> modelMapper.map(list,UserDto.class))
               .collect(Collectors.toList());
       return userDtoList;
    }

    @Override
    public UserDto addUser(UserDto userDto) {

        User user = modelMapper.map(userDto, User.class);
        User saveUser = userRepository.save(user);
        return modelMapper.map(saveUser,UserDto.class);
    }

    @Override
    public UserDto getUserByMeterNumber(Integer number) {
        User user = userRepository.getUserByMeterNo(number);
        if (user != null)
        {
            return modelMapper.map(user,UserDto.class);
        }
        else {
            throw new ResourceNotFoundException("User is not exist with given meter number : "+number);
        }
    }

    @Override
    public Boolean deleteUserByNo(Integer meterNumber){

        User user = userRepository.getUserByMeterNo(meterNumber);
        if (user != null)
        {
                userRepository.deleteByMeterNo(meterNumber);
                return true;
        }
        else
        {
            return false;
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
            return modelMapper.map(updateUser,UserDto.class);
        }
        else
        {
            throw new ResourceNotFoundException("User is not exist with given meter number : " + number);
        }


    }


}
