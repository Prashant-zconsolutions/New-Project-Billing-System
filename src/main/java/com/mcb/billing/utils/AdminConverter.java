package com.mcb.billing.utils;

import com.mcb.billing.dto.AdminDto;
import com.mcb.billing.entity.Admin;

public class AdminConverter {


    public static AdminDto convertToAdminDto(Admin admin)
    {
        return new AdminDto(
                admin.getAdminId(),
                admin.getAdminUserName(),
                admin.getAdminPassword()
        );
    }

    public static Admin convertToAdminEntity(AdminDto adminDto)
    {
        return new Admin(
                adminDto.getAdminId(),
                adminDto.getAdminUserName(),
                adminDto.getAdminPassword()
        );
    }

}
