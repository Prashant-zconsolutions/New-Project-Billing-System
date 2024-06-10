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

    @GetMapping("/getAllRatesByUserType/{userType}")
    public ResponseEntity<List<RateDto>> getAllRates(@PathVariable("userType") String userType)
    {
        System.out.println(" >>>> >>>>>>>>> "+userType);
        List<RateDto> rateDto = rateService.getAllRatesByUserType(userType);
        return new ResponseEntity<>(rateDto, HttpStatus.OK);
    }

    @GetMapping("/getRateById/{rateId}")
    public ResponseEntity<RateDto> getRateByRateId(@PathVariable Integer rateId)
    {
        RateDto rateDto =  rateService.getRateByRateId(rateId);
        return new ResponseEntity<>(rateDto,HttpStatus.OK);
    }


    @PostMapping("/addRate")
    public ResponseEntity<RateDto> addRate(@RequestBody RateDto rateDto)
    {
        RateDto saveRateDto = rateService.addRate(rateDto);
        return new ResponseEntity<>(saveRateDto,HttpStatus.OK);
    }

    @PutMapping("/updateRate/{rateId}")
    public ResponseEntity<RateDto> updateRateById(@PathVariable Integer rateId,@RequestBody RateDto rateDto)
    {
        RateDto updatedRate = rateService.updateRateById(rateId,rateDto);
        return new ResponseEntity<>(updatedRate,HttpStatus.OK);
    }


    @DeleteMapping("/deleteRateById/{rateId}")
    public ResponseEntity<String> deleteRateById(@PathVariable Integer rateId)
    {
        String msg =  rateService.deleteRateById(rateId);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }






}
