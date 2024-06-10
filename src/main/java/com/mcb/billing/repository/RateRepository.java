package com.mcb.billing.repository;

import com.mcb.billing.entity.Rate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RateRepository extends JpaRepository <Rate,Integer>{


    @Query(value = "SELECT * FROM rates where user_type = :user_type",nativeQuery = true)
    List<Rate> getAllRatesByUserType(@Param("user_type") String userType);

    @Query(value = "SELECT * FROM rates where rate_id = :rate_id",nativeQuery = true)
    Rate getRateById(@Param("rate_id") Integer rate_id);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM rates WHERE rate_id =:rate_id",nativeQuery = true)
    void deleteRateById(@Param("rate_id") Integer rate_id);

}
