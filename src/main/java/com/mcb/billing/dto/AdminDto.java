package com.mcb.billing.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

    private Integer adminId;

    @NotEmpty(message = "Admin username cannot be null")
    @Size(min = 3,message = "Minimum 3 character required")
    private String adminUserName;

    @NotEmpty(message = "Admin password cannot be null")
    private String adminPassword;

}
