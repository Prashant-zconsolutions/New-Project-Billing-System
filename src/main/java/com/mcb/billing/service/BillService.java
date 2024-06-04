package com.mcb.billing.service;

import com.mcb.billing.dto.BillDto;

import java.util.List;

public interface BillService {

    List<BillDto> getAllBills();

    BillDto addBill(BillDto billDto,Integer number,Integer rate);
}
