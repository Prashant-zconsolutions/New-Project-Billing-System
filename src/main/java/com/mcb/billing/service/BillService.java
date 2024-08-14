package com.mcb.billing.service;

import com.mcb.billing.dto.BillDto;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BillService {

    Page<BillDto> getAllBills(Integer pageNumber, Integer pageSize);

    Map getAllBillsUsingMonth(Integer month,Integer year);

    List<BillDto> getAllBillsBySpecificUser(Integer meterNumber);

    BillDto getAllBillByMeterNoAndDate(Integer meterNumber, LocalDate date);

    BillDto addBill(BillDto billDto,Integer meterNumber);

    Boolean deleteByBillNo(Integer billNumber);

    BillDto getBillByNo(Integer billNumber);

    BillDto updateBillByBillNumber(Integer billNumber,BillDto billDto);

    void exportBill(BillDto billDto) throws IOException;
}
