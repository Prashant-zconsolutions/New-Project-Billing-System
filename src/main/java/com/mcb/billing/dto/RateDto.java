package com.mcb.billing.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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


    @Pattern(regexp="^(home|commercial)$", message="Invalid value. Only 'home' or 'commercial' allowed.")
    private String userType;

    @NotNull(message = "Rate user Price cannot be null")
    private Double userPrice;

    @NotNull(message = "Minimum range of rate cannot be null")
    private Integer rateMin;

    @NotNull(message = "Maximum range of rate cannot be null")
    private Integer rateMax;

}
