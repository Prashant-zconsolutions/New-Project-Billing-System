package com.mcb.billing.controller;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.service.BillService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class BillController {


    @Autowired
    private BillService billService;

    @GetMapping("/getAllBills")
    public ResponseEntity<List<BillDto>> getAllBills()
    {
        List<BillDto> billDto = billService.getAllBills();
        return new ResponseEntity<>(billDto, HttpStatus.OK);
    }

    @GetMapping("/getBillByMeterNumber/{meterNumber}")
    public ResponseEntity<List<BillDto>> getAllBillsBySpecificUser(@PathVariable Integer meterNumber)
    {
        List<BillDto> billDtoList = billService.getAllBillsBySpecificUser(meterNumber);
        return new ResponseEntity<>(billDtoList,HttpStatus.OK);
    }

    @PostMapping("/addBill/{number}")
    public ResponseEntity<BillDto> addBill(@PathVariable Integer number,
                                           @RequestBody BillDto billDto)
    {
        BillDto billDto1 = billService.addBill(billDto,number);
        return new ResponseEntity<>(billDto1,HttpStatus.OK);
    }


    @GetMapping("/getBillByNo/{number}")
    public ResponseEntity<BillDto> getBillByNo(@PathVariable Integer number)
    {
        BillDto billDto = billService.getBillByNo(number);
        return new ResponseEntity<>(billDto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteBillByNo/{billNumber}")
    public ResponseEntity<String> deleteBillByNumber(@PathVariable Integer billNumber)
    {
        String msg = billService.deleteByBillNo(billNumber);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }


    @GetMapping("/getBillByMeterNoAndDate{meterNumber}{date}")
    public ResponseEntity<BillDto> getBillByMeterNoAndDate(@RequestParam("meterNumber") Integer meterNumber,
                                                           @RequestParam("date") LocalDate date)
    {
        System.out.println(meterNumber+" >>> "+date);
        BillDto billDto = billService.getAllBillByMeterNoAndDate(meterNumber, date);
        return new ResponseEntity<>(billDto,HttpStatus.OK);
    }




}
