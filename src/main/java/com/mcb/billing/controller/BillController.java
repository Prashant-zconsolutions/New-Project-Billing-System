package com.mcb.billing.controller;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addBill/{number}")
    public ResponseEntity<BillDto> addBill(@PathVariable Integer number,
                                           @RequestParam(value = "rate", required = false) Integer rate,
                                           @RequestBody BillDto billDto)
    {
        System.out.println(rate);
        BillDto billDto1 = billService.addBill(billDto,number,rate);
        return new ResponseEntity<>(billDto1,HttpStatus.OK);
    }
}
