package com.mcb.billing.controller;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.service.BillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class BillController {


    @Autowired
    private BillService billService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/getAllBills")
    public ResponseEntity<Page<BillDto>> getAllBills(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize)
    {
        Page<BillDto> billDto = billService.getAllBills(pageNumber,pageSize);
        return new ResponseEntity<>(billDto, HttpStatus.OK);
    }

    @GetMapping("/getBillByMeterNumber/{meterNumber}")
    public ResponseEntity<List<BillDto>> getAllBillsBySpecificUser(@PathVariable Integer meterNumber)
    {
        List<BillDto> billDtoList = billService.getAllBillsBySpecificUser(meterNumber);
        return new ResponseEntity<>(billDtoList,HttpStatus.OK);
    }

    @PostMapping("/addBill/{meterNumber}")
    public ResponseEntity<BillDto> addBill(@PathVariable Integer meterNumber,
                                           @Valid @RequestBody BillDto billDto)
    {
        BillDto billDto1 = billService.addBill(billDto,meterNumber);
        return new ResponseEntity<>(billDto1,HttpStatus.OK);
    }


    @GetMapping("/getBillByNo/{billNumber}")
    public ResponseEntity<BillDto> getBillByNo(@PathVariable Integer billNumber)
    {
        BillDto billDto = billService.getBillByNo(billNumber);
        return new ResponseEntity<>(billDto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteBillByNo/{billNumber}")
    public ResponseEntity<String> deleteBillByNumber(@PathVariable Integer billNumber)
    {

        if(billService.deleteByBillNo(billNumber))
        {
            String successMessage = messageSource.getMessage("bill.delete.success", null, null);
            return ResponseEntity.ok(successMessage);
        }else {
            String notFoundMessage = messageSource.getMessage("bill.delete.notfound", new Object[]{billNumber}, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
    }



    @PutMapping("/updateBillByBillNo/{billNumber}")
    public ResponseEntity<BillDto> updateBillByBillNumber(@PathVariable Integer billNumber,@Valid @RequestBody BillDto billDto)
    {
        BillDto updatedBill =  billService.updateBillByBillNumber(billNumber,billDto);
        return new ResponseEntity<>(updatedBill,HttpStatus.OK);
    }



    @GetMapping("/getAllBillsUsingMonth{month}{year}")
    public ResponseEntity<Map> getAllBillsUsingMonth(@RequestParam("month") Integer month,
                                                     @RequestParam("year") Integer year)
    {
        Map list = billService.getAllBillsUsingMonth(month,year);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }





}
