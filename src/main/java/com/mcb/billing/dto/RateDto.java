package com.mcb.billing.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {

    private int rateId;
    private String userType;
    private double userPrice;
    private float rateMin;
    private float rateMax;

}
