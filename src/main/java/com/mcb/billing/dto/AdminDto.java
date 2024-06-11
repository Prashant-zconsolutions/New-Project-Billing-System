package com.mcb.billing.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
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
    private String adminUserName;

    @NotEmpty(message = "Admin password cannot be null")
    private String adminPassword;

}
