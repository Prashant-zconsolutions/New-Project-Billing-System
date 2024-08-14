package com.mcb.billing.controller;

import com.mcb.billing.dto.UserDto;
import com.mcb.billing.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;


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

    @DeleteMapping("/deleteUser/{meterNumber}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer meterNumber,
                                             @RequestHeader(name = "Accept-Language", required = false) Locale locale)
    {

                if(userService.deleteUserByNo(meterNumber))
                {
                    String successMessage = messageSource.getMessage("user.delete.success", null, locale);
                    return ResponseEntity.ok(successMessage);

                }else {
                    String notFoundMessage = messageSource.getMessage("user.delete.notfound", new Object[]{meterNumber}, locale);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
                }

    }

    @GetMapping("/getUserByNumber/{meterNumber}")
    public ResponseEntity<UserDto> getUserByMeterNumber(@PathVariable Integer meterNumber)
    {
       UserDto  userDto = userService.getUserByMeterNumber(meterNumber);
       return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PutMapping("/updateUser/{meterNumber}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer meterNumber ,@Valid @RequestBody UserDto userDto)
    {
        UserDto dto =  userService.updateUser(meterNumber,userDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }



}
