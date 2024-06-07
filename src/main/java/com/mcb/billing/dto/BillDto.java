package com.mcb.billing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mcb.billing.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BillDto {

    private int billNumber;
    private LocalDate billDate;
    private int billUnit;
    private double billAmount;
    private UserDto user;

}
