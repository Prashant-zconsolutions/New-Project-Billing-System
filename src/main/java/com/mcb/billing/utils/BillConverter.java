package com.mcb.billing.utils;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.dto.UserDto;
import com.mcb.billing.entity.Bill;
import com.mcb.billing.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;

public class BillConverter {

    public static BillDto convertToUserDto(Bill bill)
    {
        UserDto userDto =  UserConverter.convertToUserDto(bill.getUser());
        return new BillDto(
                bill.getBillNumber(),
                bill.getBillDate(),
                bill.getBillUnit(),
                bill.getBillAmount(),
                userDto
        );


//
//        TypeMap<Bill, BillDto> dtoTypeMap = modelMapper.typeMap(Bill.class, BillDto.class).addMappings(mapper -> {
//            mapper.map(Bill::getBillNumber, BillDto::setBillNumber);
//            mapper.map(Bill::getBillDate, BillDto::setBillDate);
//            mapper.map(Bill::getBillUnit, BillDto::setBillUnit);
//            mapper.map(Bill::getBillAmount, BillDto::setBillAmount);
//            mapper.map(Bill::getUser, BillDto::setUser);
//        });
//        return modelMapper.map(bill, BillDto.class);


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
