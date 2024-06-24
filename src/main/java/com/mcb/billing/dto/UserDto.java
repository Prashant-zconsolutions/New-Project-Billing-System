package com.mcb.billing.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Integer meterNumber;

    @NotEmpty(message = "First name cannot be null")
    private String firstName;

    @NotEmpty(message = "Last name cannot be null")
    private String lastName;

    @NotEmpty(message = "Address cannot be null")
    private String address;

    @NotEmpty(message = "City cannot be null")
    private String city;

    @NotEmpty(message = "State cannot be null")
    private String state;

    @NotEmpty(message = "Email name cannot be null")
    @Pattern(regexp="^\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b$", message="Invalid email address")
    private String email;

    @NotEmpty(message = "Phone number cannot be null")
    @Pattern(regexp="^[789]\\d{9}$", message="Invalid phone number")
    private String phoneNumber;

    @NotEmpty(message = "UserType cannot be null")
    @Pattern(regexp="^(home|commercial)$", message="Invalid value. Only 'home' or 'commercial' allowed.")
    private String userType;

}
