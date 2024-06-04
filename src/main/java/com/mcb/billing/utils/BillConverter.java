package com.mcb.billing.utils;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.entity.Bill;

public class BillConverter {

    public static BillDto convertToUserDto(Bill bill)
    {
        return new BillDto(
                bill.getBillNumber(),
                bill.getBillDate(),
                bill.getBillUnit(),
                bill.getBillAmount(),
                bill.getUser()
        );
    }

    public static Bill convertToBillEntity(BillDto billDto)
    {
        return new Bill(
                billDto.getBillNumber(),
                billDto.getBillDate(),
                billDto.getBillUnit(),
                billDto.getBillAmount(),
                billDto.getUser()
        );
    }
}
