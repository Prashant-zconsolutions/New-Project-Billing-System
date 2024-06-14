package com.mcb.billing.repository;

import com.mcb.billing.entity.Admin;
import com.mcb.billing.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Integer> {



    @Query(value = "SELECT * FROM admin",nativeQuery = true)
    List<Admin> getAllAdmins();


    @Query(value = "SELECT * FROM admin WHERE admin_id=:number",nativeQuery = true)
    Admin getAdminById(@Param("number") Integer adminId);

    @Query(value = "SELECT * FROM admin WHERE admin_username=:userName",nativeQuery = true)
    Admin getAdminByUsername(@Param("userName") String userName);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM admin where admin_id=:number",nativeQuery = true)
    void deleteByAdminId(@Param("number") Integer adminId);




}
