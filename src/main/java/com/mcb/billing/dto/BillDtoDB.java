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
public class BillDtoDB {


    private int billNumber;
    private LocalDate billDate;
    private Integer billUnit;
    private Double billAmount;
    private Integer meterNumber;
    private String consumption;


}
