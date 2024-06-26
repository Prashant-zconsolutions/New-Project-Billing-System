package com.mcb.billing.controller;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.service.BillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicController {


    @Autowired
    private BillService billService;

    @GetMapping("/getBillByMeterNoAndDate{meterNumber}{date}")
    public ResponseEntity<BillDto> getBillByMeterNoAndDate(@RequestParam("meterNumber") Integer meterNumber,
                                                           @RequestParam("date") LocalDate date)
    {
        System.out.println(meterNumber+" >>> "+date);
        BillDto billDto = billService.getAllBillByMeterNoAndDate(meterNumber, date);
        return new ResponseEntity<>(billDto, HttpStatus.OK);
    }

    @GetMapping("/getAllBillsUsingMonth")
    public ResponseEntity<List> getAllBillsUsingMonth()
    {
        List list = billService.getAllBillsUsingMonth();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


}
