package com.mcb.billing.utils;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.dto.UserDto;
import com.mcb.billing.entity.Bill;
import com.mcb.billing.entity.User;

public class BillConverter {

    public static BillDto convertToUserDto(Bill bill)
    {
//        System.out.println(bill.getUser().getFirstName());
        UserDto userDto =  UserConverter.convertToUserDto(bill.getUser());
//        System.out.println(userDto.getFirstName());
        return new BillDto(
                bill.getBillNumber(),
                bill.getBillDate(),
                bill.getBillUnit(),
                bill.getBillAmount(),
                userDto

        );
    }

    public static Bill convertToBillEntity(BillDto billDto)
    {
        User user = UserConverter.convertToUserEntity(billDto.getUser());
        return new Bill(
                billDto.getBillNumber(),
                billDto.getBillDate(),
                billDto.getBillUnit(),
                billDto.getBillAmount(),
                user
        );
    }
}
