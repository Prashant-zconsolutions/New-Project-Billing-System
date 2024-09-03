package com.mcb.billing.controller;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.entity.Bill;
import com.mcb.billing.service.BillService;
import com.mcb.billing.serviceImpl.BillServiceImpl;
import jakarta.validation.Valid;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
                                                           @RequestParam("date") LocalDate BillDate) {
        BillDto billDto = billService.getAllBillByMeterNoAndDate(meterNumber, BillDate);
        return new ResponseEntity<>(billDto, HttpStatus.OK);
    }


    @GetMapping("/exportBill{meterNumber}{date}")
    public String exportBill(@RequestParam("meterNumber") Integer meterNumber,
                             @RequestParam("date") LocalDate BillDate) {
        try {
            BillDto billDto = billService.getAllBillByMeterNoAndDate(meterNumber, BillDate);
            billService.exportBill(billDto);
            return "File saved to local storage successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error saving file to local storage: " + e.getMessage();
        }
    }

    @GetMapping("/importBill")
    public String importBill(){
        try {
            List<BillDto> billDtoList = billService.importBill();
//            return new ResponseEntity<>(billDtoList, HttpStatus.OK);
            return "File upload successfully!";
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return null;
    }



    @GetMapping("/readFileWithSaxParser")
    public String readBillUsingSaxParser() throws OpenXML4JException, SAXException {

        try {

            String filePath = "D:\\Resource" + "/ELC Bill 2.xlsx";

            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                return "File does not exist or is not a file.";
            }
            new ExcelSaxHandler().readExcelFile(file);
            return "Completed";
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return "Failure";
    }







}
