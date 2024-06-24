package com.mcb.billing.controller;

import com.mcb.billing.dto.RateDto;
import com.mcb.billing.service.RateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/users")
public class RateController {


    @Autowired
    private RateService rateService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/getAllRatesByUserType/{userType}")
    public ResponseEntity<List<RateDto>> getAllRates(@PathVariable("userType") String userType)
    {
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
    public ResponseEntity<RateDto> addRate(@Valid @RequestBody RateDto rateDto)
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
    public ResponseEntity<String> deleteRateById(@PathVariable Integer rateId,
                                                 @RequestHeader(name = "Accept-Language", required = false) Locale locale)
    {

        if(rateService.deleteRateById(rateId))
        {
            String successMessage = messageSource.getMessage("rate.delete.success", null, locale);
            return ResponseEntity.ok(successMessage);
        }
        else
        {
            String notFoundMessage = messageSource.getMessage("rate.delete.notfound", new Object[]{rateId}, locale);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }

    }






}
