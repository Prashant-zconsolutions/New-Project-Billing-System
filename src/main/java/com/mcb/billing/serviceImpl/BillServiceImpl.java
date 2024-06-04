package com.mcb.billing.serviceImpl;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.entity.Bill;
import com.mcb.billing.entity.User;
import com.mcb.billing.repository.BillRepository;
import com.mcb.billing.repository.UserRepository;
import com.mcb.billing.service.BillService;
import com.mcb.billing.utils.BillConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {


    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<BillDto> getAllBills() {
        List<Bill> billList =  billRepository.getAllBills();
        List<BillDto> billDtos = billList.stream()
                .map(BillConverter::convertToUserDto)
                .collect(Collectors.toList());

        return billDtos;
    }

    @Override
    public BillDto addBill(BillDto billDto,Integer number,Integer rate) {
        Bill bill = BillConverter.convertToBillEntity(billDto);
        User user = userRepository.getUserByMeterNo(number);

        int homeRate = 5;
        int commercialRate = 8;
        System.out.println(" >>>>> "+rate);
        if(rate != null)
        {
            bill.setBillAmount( rate * bill.getBillUnit());

        }
        else {
            if (user.getUserType().equalsIgnoreCase("home"))
            {
                System.out.println("Home : " + homeRate);
                bill.setBillAmount(homeRate * bill.getBillUnit());
            }

            if (user.getUserType().equalsIgnoreCase("commercial"))
            {
                System.out.println("Home : "+ homeRate);
                bill.setBillAmount( commercialRate * bill.getBillUnit());
            }

        }

        bill.setUser(user);
        Bill bill1 =  billRepository.save(bill);
        return BillConverter.convertToUserDto(bill1);
    }
}
