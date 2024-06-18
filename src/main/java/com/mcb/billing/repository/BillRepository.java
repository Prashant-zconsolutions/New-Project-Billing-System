package com.mcb.billing.repository;

import com.mcb.billing.entity.Bill;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {


    @Query(value = "SELECT * FROM bills",nativeQuery = true)
    List<Bill> getAllBills();

    @Query(value = "SELECT * FROM bills WHERE meter_number =:meter_number",nativeQuery = true)
    List<Bill> getAllBillsByMeterNumber(@Param("meter_number") Integer meterNumber);


    @Query(value = "SELECT * FROM bills WHERE meter_number = ?1 AND bill_date = ?2",nativeQuery = true)
    Bill getBillByMeterNoAndDate(Integer meterNumber,LocalDate date);


    @Query(value = "SELECT * FROM bills WHERE EXTRACT(MONTH FROM bill_date) = ?1 AND EXTRACT(YEAR FROM bill_date) = ?2 AND meter_number = ?3",nativeQuery = true)
    Bill getBillByMeterNoAndYearAndMonth(Integer month,Integer year,Integer meterNumber);


    @Query(value = "SELECT * FROM bills b WHERE EXTRACT(MONTH FROM bill_date) = ?1 AND EXTRACT(YEAR FROM bill_date) = ?2",nativeQuery = true)
    List<Bill> getAllBillByMonthAndYear(Integer month,Integer year);



    @Transactional
    @Modifying
    @Query(value = "DELETE FROM bills WHERE bill_number =:bill_number",nativeQuery = true)
    void deleteBillByNo(@Param("bill_number") Integer billNumber);

    @Query(value = "SELECT * FROM bills WHERE bill_number =:bill_number",nativeQuery = true)
    Bill getBillByBillNo(@Param("bill_number") Integer billNumber);


}
