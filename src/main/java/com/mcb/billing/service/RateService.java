package com.mcb.billing.service;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.dto.RateDto;

import java.util.List;

public interface RateService {

    List<RateDto> getAllRatesByUserType(String userType);

    RateDto addRate(RateDto rateDto);

    RateDto updateRateById(Integer rateId,RateDto rateDto);

    String deleteRateById(Integer rateId);

    RateDto getRateByRateId(Integer rateId);
}
