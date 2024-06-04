package com.mcb.billing.repository;

import com.mcb.billing.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM users",nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "SELECT * FROM users WHERE meter_number=:number",nativeQuery = true)
    User getUserByMeterNo(@Param("number") Integer number);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users where meter_number=:number",nativeQuery = true)
    void deleteByMeterNo(@Param("number") Integer number);

}
