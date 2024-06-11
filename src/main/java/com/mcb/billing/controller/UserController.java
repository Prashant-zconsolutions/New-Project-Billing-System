package com.mcb.billing.controller;

import com.mcb.billing.dto.UserDto;
import com.mcb.billing.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> dtoList = userService.getAllUsers();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto dto = userService.addUser(userDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{number}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer number)
    {
        try
        {
            String s = userService.deleteUserByNo(number);
            return new ResponseEntity<>(s,HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.OK);
        }


    }

    @GetMapping("/getUserByNumber/{number}")
    public ResponseEntity<UserDto> getUserByMeterNumber(@PathVariable Integer number)
    {
       UserDto  userDto = userService.getUserByMeterNumber(number);
       return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PutMapping("/updateUser/{number}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer number ,@Valid @RequestBody UserDto userDto)
    {
        UserDto dto =  userService.updateUser(number,userDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }



}
