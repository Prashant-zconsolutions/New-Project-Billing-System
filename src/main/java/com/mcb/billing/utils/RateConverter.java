package com.mcb.billing.utils;

import com.mcb.billing.dto.RateDto;
import com.mcb.billing.entity.Rate;

public class RateConverter {

    public static RateDto convertToRateDto(Rate rate)
    {
        return new RateDto(
                rate.getRateId(),
                rate.getUserType().trim(),
                rate.getUserPrice(),
                rate.getRateMin(),
                rate.getRateMax()
        );
    }

    public static Rate convertToRateEntity(RateDto rateDto)
    {
        return new Rate(
                rateDto.getRateId(),
                rateDto.getUserType().trim(),
                rateDto.getUserPrice(),
                rateDto.getRateMin(),
                rateDto.getRateMax()
        );
    }
}
