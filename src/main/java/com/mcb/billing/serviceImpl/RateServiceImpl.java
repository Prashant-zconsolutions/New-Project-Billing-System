package com.mcb.billing.serviceImpl;

import com.mcb.billing.dto.RateDto;
import com.mcb.billing.entity.Rate;
import com.mcb.billing.repository.RateRepository;
import com.mcb.billing.service.RateService;
import com.mcb.billing.utils.RateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {


    @Autowired
    private RateRepository rateRepository;

    @Override
    public List<RateDto> getAllRates(String userType) {

        List<Rate> rate = rateRepository.getAllRates(userType);
        List<RateDto> rateDtos = rate.stream()
                .map(RateConverter::convertToRateDto)
                .collect(Collectors.toList());

        return rateDtos;


    }
}
