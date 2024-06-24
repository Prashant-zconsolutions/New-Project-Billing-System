package com.mcb.billing.service;

import com.mcb.billing.dto.BillDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BillService {

    List<BillDto> getAllBills();

    Map getAllBillsUsingMonth(Integer month,Integer year);

    List<BillDto> getAllBillsBySpecificUser(Integer meterNumber);

    BillDto getAllBillByMeterNoAndDate(Integer meterNumber, LocalDate date);

    BillDto addBill(BillDto billDto,Integer meterNumber);

    Boolean deleteByBillNo(Integer billNumber);

    BillDto getBillByNo(Integer billNumber);

    BillDto updateBillByBillNumber(Integer billNumber,BillDto billDto);
}
