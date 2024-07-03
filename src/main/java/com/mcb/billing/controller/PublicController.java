package com.mcb.billing.controller;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.service.BillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    @Autowired
    private BillService billService;

    @GetMapping("/getBillByMeterNoAndDate{meterNumber}{date}")
    public ResponseEntity<BillDto> getBillByMeterNoAndDate(@RequestParam("meterNumber") Integer meterNumber,
                                                           @RequestParam("date") LocalDate BillDate)
    {
        BillDto billDto = billService.getAllBillByMeterNoAndDate(meterNumber, BillDate);
        return new ResponseEntity<>(billDto, HttpStatus.OK);
    }




}
