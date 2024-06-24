package com.mcb.billing.serviceImpl;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.dto.BillDtoDB;
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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;



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
    public Boolean deleteByBillNo(Integer number) {
        Bill bill = billRepository.getBillByBillNo(number);
        if (bill != null){
            billRepository.deleteBillByNo(number);
            return true;
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
    public Map getAllBillsUsingMonth(Integer month,Integer year) {

        String sql ="SELECT b.*, \n" +
                "    CASE \n" +
                "        WHEN b.bill_unit >= 0 AND b.bill_unit < 100 THEN 'Low' \n" +
                "        WHEN b.bill_unit >= 100 AND b.bill_unit < 300 THEN 'Medium' \n" +
                "        WHEN b.bill_unit >= 300 THEN 'High' \n" +
                "        ELSE 'Unknown' \n" +
                "    END AS consumption \n" +
                "FROM bills b \n" +
                "WHERE EXTRACT(MONTH FROM bill_date) = "+month+" AND EXTRACT(YEAR FROM bill_date) = "+year+" ;";

          List<BillDtoDB> list =  jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(BillDtoDB.class));

        Map<String, List<BillDtoDB>> listMap = list.stream().collect(Collectors.groupingBy(BillDtoDB::getConsumption));
        return listMap;

    }
}
