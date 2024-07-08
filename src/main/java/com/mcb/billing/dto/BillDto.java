package com.mcb.billing.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    private int billNumber;

    @NotNull(message = "Date Cannot be null")
    private LocalDate billDate;

    @NotNull(message = "Bill unit cannot be null")
    @Min(value = 1, message = "Value must be greater than 0")
    private Integer billUnit;

    private Double billAmount;

    private UserDto user;

}
