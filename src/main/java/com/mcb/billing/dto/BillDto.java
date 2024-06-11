package com.mcb.billing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BillDto {

    private int billNumber;

    @NotNull(message = "Date Cannot be null")
    private LocalDate billDate;

    @NotNull(message = "Bill unit cannot be null")
    private Integer billUnit;

    private double billAmount;

    private UserDto user;

}
