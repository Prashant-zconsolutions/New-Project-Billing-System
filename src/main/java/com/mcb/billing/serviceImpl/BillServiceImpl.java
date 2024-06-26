package com.mcb.billing.serviceImpl;

import com.mcb.billing.controller.AdminController;
import com.mcb.billing.dto.BillDto;
import com.mcb.billing.ecxception.ResourceNotFoundException;
import com.mcb.billing.entity.Bill;
import com.mcb.billing.entity.Rate;
import com.mcb.billing.entity.User;
import com.mcb.billing.repository.BillRepository;
import com.mcb.billing.repository.RateRepository;
import com.mcb.billing.repository.UserRepository;
import com.mcb.billing.service.BillService;
import com.mcb.billing.utils.BillConverter;
import com.mcb.billing.utils.UserConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {


    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RateRepository rateRepository;


    @Override
    public List<BillDto> getAllBills() {
        List<Bill> billList =  billRepository.getAllBills();
        List<BillDto> billDtos = billList.stream()
                .map(BillConverter::convertToUserDto)
                .collect(Collectors.toList());
        return billDtos;
    }

    @Override
    public List<BillDto> getAllBillsBySpecificUser(Integer meterNumber) {

        List<Bill> billList = billRepository.getAllBillsByMeterNumber(meterNumber);
        List<BillDto> billDtoList = billList.stream()
                .map(BillConverter::convertToUserDto)
                .collect(Collectors.toList());

        return billDtoList;

    }

    @Override
    public BillDto getAllBillByMeterNoAndDate(Integer meterNumber, LocalDate date) {

        Bill bill = billRepository.getBillByMeterNoAndDate(meterNumber,date);
        if(bill == null)
        {
            throw new ResourceNotFoundException("Bill is not exist with given meter number: " + meterNumber+" and date: "+date);
        }
        else
        {
            return BillConverter.convertToUserDto(bill);
        }

    }

    @Override
    public BillDto addBill(BillDto billDto,Integer number) {
        User user = userRepository.getUserByMeterNo(number);
        billDto.setUser(UserConverter.convertToUserDto(user));
        Bill bill = BillConverter.convertToBillEntity(billDto);



        LocalDate date =  bill.getBillDate();

        if(user == null)
        {
            throw new ResourceNotFoundException("User is not exist with given user number : " + number);
        }
        else if (checkDuplicateIdAndDate(number ,bill.getBillDate()))
        {
            throw new ResourceNotFoundException("Bill already created with given meter number : " + number+" and date : "+bill.getBillDate().getYear()+" "+bill.getBillDate().getMonth());
        }
        else
        {
            bill.setUser(user);
            List<Rate> rates = rateRepository.getAllRatesByUserType(user.getUserType());
            double calculatedPrice = calculatePrice(rates,bill.getBillUnit());
            bill.setBillAmount(calculatedPrice);
            Bill bill1 =  billRepository.save(bill);
            return BillConverter.convertToUserDto(bill1);
        }




    }

    private boolean checkDuplicateIdAndDate(Integer number, LocalDate billDate) {

//        boolean val = false;

        Bill bill = billRepository.getBillByMeterNoAndYearAndMonth(billDate.getMonthValue(),billDate.getYear(),number);
        return bill == null ? false : true;

//        if(bill == null)
//        {
//            return val;
//        }
//        else {
//            return val = true;
//        }

        /*
        List<Bill> billList =  billRepository.getAllBillsByMeterNumber(number);
//        val = billList.stream()
//                .map(Bill::getBillDate) // Get all bill dates
//                .anyMatch(date -> date != null && date.equals(billDate));
//
        int newYear = billDate.getYear();
        String newMonth = billDate.getMonth().toString();

        for(Bill bill : billList)
        {
            int oldYear = bill.getBillDate().getYear();
            String oldMonth = bill.getBillDate().getMonth().toString();

            if(newMonth.equals(oldMonth) && newYear == oldYear)
            {
                val = true;
                break;
            }
        }

         */
//        return val;

    }

    private double calculatePrice(List<Rate> rateList, int billUnit) {

        Collections.sort(rateList, Comparator.comparing(Rate::getRateMin));
        float billPrice=0;

        for(int i=0 ; i < rateList.size() ; i++)
        {

            Rate rate = rateList.get(i);
            float maxUnits = rate.getRateMax() == 0
                    ?
                    billUnit
                    :
                    rate.getRateMax() - rate.getRateMin();

            if(billUnit > maxUnits)
            {
                billPrice += rate.getUserPrice() * maxUnits;
                billUnit = (int) (billUnit - maxUnits);
            }
            else
            {
                billPrice += rate.getUserPrice() * billUnit;
                break;
            }

        }

        return billPrice;
    }

    @Override
    public String deleteByBillNo(Integer number) {
        Bill bill = billRepository.getBillByBillNo(number);
        if (bill != null){
            billRepository.deleteBillByNo(number);
            return "Bill Deleted Successfully!";
        }else{
            throw new ResourceNotFoundException("Bill is not exist with given bill number : " + number);
        }
    }

    @Override
    public BillDto getBillByNo(Integer number) {
        Bill bill = billRepository.getBillByBillNo(number);
        if(bill != null)
        {
            return BillConverter.convertToUserDto(bill);
        }
        else
        {
            throw new ResourceNotFoundException("Bill is not exist with given bill number : " + number);
        }
    }

    @Override
    public BillDto updateBillByBillNumber(Integer billNumber, BillDto billDto) {

        Bill bill = billRepository.getBillByBillNo(billNumber);

        if(bill == null)
        {
            throw new ResourceNotFoundException("Bill is not exist with given bill number : " + billNumber);
        }
        else
        {
            bill.setBillDate(billDto.getBillDate());
            bill.setBillUnit(billDto.getBillUnit());

            List<Rate> rates = rateRepository.getAllRatesByUserType(bill.getUser().getUserType());
            double calculatedPrice = calculatePrice(rates,billDto.getBillUnit());
            bill.setBillAmount(calculatedPrice);

           Bill saveBill =  billRepository.save(bill);
           return BillConverter.convertToUserDto(saveBill);
        }

    }

    @Override
    public List getAllBillsUsingMonth() {
        Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

        List<Bill> billList =  billRepository.getAllBillByMonthAndYear(01,2024);

        List<BillDto> billDtos = billList.stream()
                .map(BillConverter::convertToUserDto)
                .collect(Collectors.toList());


        List<Map<String, Object>> list = billDtos.stream()
                .map(bill -> {



                    int unit = bill.getBillUnit();
                    String consumptionLevel;
                    if (unit >= 0 && unit <= 100) {
                        consumptionLevel = "Low consumption";
                    } else if (unit > 100 && unit <= 300) {
                        consumptionLevel = "Medium consumption";
                    } else if (unit > 300) {
                        consumptionLevel = "High consumption";
                    } else {
                        consumptionLevel = "Unknown"; // Handle unexpected values
                    }

                   return  Map.of("Bill number",bill.getBillNumber(),
                            "Bill unit",bill.getBillUnit(),
                           "User",bill.getUser(),
                           "Bill Consumption",consumptionLevel
                             );

                }).collect(Collectors.toList());

            return list;
    }


//    public static Map Consumption()
//    {
//
//    }
}
