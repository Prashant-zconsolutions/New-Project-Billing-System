package com.mcb.billing.repository;

import com.mcb.billing.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RateRepository extends JpaRepository <Rate,Integer>{


    @Query(value = "SELECT * FROM rates where user_type = :user_type",nativeQuery = true)
    List<Rate> getAllRates(@Param("user_type") String userType);

}
