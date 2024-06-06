package com.mcb.billing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer meterNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String email;
    private String phoneNumber;
    private String userType;

}