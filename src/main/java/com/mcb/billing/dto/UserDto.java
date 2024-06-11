package com.mcb.billing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @NotEmpty(message = "First cannot be null")
    @Pattern(regexp="^[789]\\d{9}$", message="Invalid phone number")
    private String phoneNumber;

    @NotEmpty(message = "UserType cannot be null")
    @Pattern(regexp="^(home|commercial)$", message="Invalid value. Only 'home' or 'commercial' allowed.")
    private String userType;

}
