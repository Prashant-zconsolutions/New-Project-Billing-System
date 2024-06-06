package com.mcb.billing.service;

import com.mcb.billing.dto.BillDto;

import java.time.LocalDate;
import java.util.List;

public interface BillService {

    List<BillDto> getAllBills();

    List<BillDto> getAllBillsBySpecificUser(Integer meterNumber);

    BillDto getAllBillByMeterNoAndDate(Integer meterNumber, LocalDate date);

    BillDto addBill(BillDto billDto,Integer number);

    String deleteByBillNo(Integer number);

    BillDto getBillByNo(Integer number);
}
