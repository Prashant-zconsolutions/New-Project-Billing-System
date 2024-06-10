package com.mcb.billing.serviceImpl;

import com.mcb.billing.dto.RateDto;
import com.mcb.billing.ecxception.ResourceNotFoundException;
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
    public List<RateDto> getAllRatesByUserType(String userType) {

        List<Rate> rate = rateRepository.getAllRatesByUserType(userType);
        List<RateDto> rateDtos = rate.stream()
                .map(RateConverter::convertToRateDto)
                .collect(Collectors.toList());
        return rateDtos;
    }

    @Override
    public RateDto addRate(RateDto rateDto) {

        Rate rate = RateConverter.convertToRateEntity(rateDto);
        Rate saveRate = rateRepository.save(rate);

        return RateConverter.convertToRateDto(saveRate);
    }

    @Override
    public RateDto updateRateById(Integer rateId, RateDto rateDto) {

        Rate rate = rateRepository.getRateById(rateId);

        if (rate == null) {
            throw new ResourceNotFoundException("Rate is not exist with given rate Id : " + rateId);
        } else {
            rate.setUserType(rateDto.getUserType());
            rate.setUserPrice(rateDto.getUserPrice());
            rate.setRateMin(rateDto.getRateMin());
            rate.setRateMax(rateDto.getRateMax());

            Rate updatedRate = rateRepository.save(rate);

            return RateConverter.convertToRateDto(updatedRate);
        }

    }

    @Override
    public String deleteRateById(Integer rateId) {

        Rate rate = rateRepository.getRateById(rateId);

        if (rate == null)
        {
            throw new ResourceNotFoundException("Rate is not exist with given rate Id : " + rateId);
        }
        else
        {
            rateRepository.deleteRateById(rateId);
            return "Rate Deleted Successfully!";
        }

    }

    @Override
    public RateDto getRateByRateId(Integer rateId) {

        Rate rate = rateRepository.getRateById(rateId);

        if (rate == null)
        {
            throw new ResourceNotFoundException("Rate is not exist with given rate Id : " + rateId);
        }
        else
        {
            return RateConverter.convertToRateDto(rate);
        }


    }

}
