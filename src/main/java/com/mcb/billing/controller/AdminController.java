package com.mcb.billing.controller;

import com.mcb.billing.dto.AdminDto;
import com.mcb.billing.service.AdminService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @Autowired
    private MessageSource messageSource;

    static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);


    @GetMapping("/getAllAdmins")
    public ResponseEntity<List<AdminDto>> getAllAdmins()
    {
        LOGGER.info("Admin Controller: Get All Admins");
         List<AdminDto> adminDtoList = adminService.getAllAdmins();
         return new ResponseEntity<>(adminDtoList, HttpStatus.OK);
    }

    @GetMapping("/getAdminById/{adminId}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable Integer adminId)
    {
        LOGGER.info("Admin Controller: Get Admin by it's ID");
        AdminDto adminDto =  adminService.getAdminById(adminId);
        return new ResponseEntity<>(adminDto,HttpStatus.OK);
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<AdminDto> createNewAdmin(@Valid @RequestBody AdminDto adminDto)
    {
        AdminDto saveAdmin =  adminService.createNewAdmin(adminDto);
        LOGGER.info("Admin Controller: Admin Created");
        return new ResponseEntity<>(saveAdmin,HttpStatus.OK);
    }

    @DeleteMapping("/deleteAdminById/{adminId}")
    public ResponseEntity<String> deleteAdminById(@PathVariable Integer adminId)
    {
       if(adminService.deleteAdminById(adminId))
       {
           String successMessage = messageSource.getMessage("admin.delete.success", null, null);
           LOGGER.info("Admin Controller: Admin Deleted Successfully");
           return ResponseEntity.ok(successMessage);

       }else {
           String notFoundMessage = messageSource.getMessage("admin.delete.notfound", new Object[]{adminId}, null);
           LOGGER.error("Admin Controller: Admin not found");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
       }
    }

    @PutMapping("/updateAdminById/{adminId}")
    public ResponseEntity<AdminDto> updateAdminById(@PathVariable Integer adminId,
                                                    @Valid @RequestBody AdminDto adminDto)
    {
        AdminDto updatedAdmin =  adminService.updateAdmin(adminId,adminDto);
        LOGGER.info("Admin Controller: Admin Update");
        return new ResponseEntity<>(updatedAdmin,HttpStatus.OK);
    }


}
