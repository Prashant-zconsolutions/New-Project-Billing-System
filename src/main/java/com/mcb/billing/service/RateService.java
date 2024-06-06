package com.mcb.billing.service;

import com.mcb.billing.dto.RateDto;

import java.util.List;

public interface RateService {

    List<RateDto> getAllRates(String userType);
}
