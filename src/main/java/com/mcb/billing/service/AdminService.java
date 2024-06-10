package com.mcb.billing.service;

import com.mcb.billing.dto.AdminDto;
import com.mcb.billing.entity.Admin;

import java.util.List;

public interface AdminService {

    List<AdminDto> getAllAdmins();

    AdminDto getAdminById(Integer adminId);

    AdminDto createNewAdmin(AdminDto adminDto);

    String deleteAdminById(Integer adminNumber);

    AdminDto updateAdmin(Integer adminId,AdminDto adminDto);

}
