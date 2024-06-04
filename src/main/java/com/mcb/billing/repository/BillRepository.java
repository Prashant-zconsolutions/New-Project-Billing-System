package com.mcb.billing.repository;

import com.mcb.billing.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {


    @Query(value = "SELECT * FROM bills",nativeQuery = true)
    List<Bill> getAllBills();
}
