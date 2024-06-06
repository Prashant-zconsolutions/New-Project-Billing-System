package com.mcb.billing.controller;

import com.mcb.billing.dto.RateDto;
import com.mcb.billing.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class RateController {


    @Autowired
    private RateService rateService;

    @GetMapping("/getAllRates/{userType}")
    public ResponseEntity<List<RateDto>> getAllRates(@RequestParam("userType") String userType)
    {
        List<RateDto> rateDto = rateService.getAllRates(userType);
        return new ResponseEntity<>(rateDto, HttpStatus.OK);
    }


}
