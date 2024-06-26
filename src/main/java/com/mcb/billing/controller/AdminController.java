package com.mcb.billing.controller;

import com.mcb.billing.dto.AdminDto;
import com.mcb.billing.service.AdminService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AdminController {


    @Autowired
    private AdminService adminService;

//    @GetMapping("/message")
//    public String getMessage()
//    {
//        return "Spring Security Enable";
//    }
//
//    @GetMapping("/current-user")
//    public String getLoggedInUser(Principal principal)
//    {
//        return principal.getName();
//    }

    Logger logger = LoggerFactory.getLogger(AdminController.class);



    @GetMapping("/getAllAdmins")
    public ResponseEntity<List<AdminDto>> getAllAdmins()
    {
        logger.info("Admin Controller: Get All Admins");
         List<AdminDto> adminDtoList = adminService.getAllAdmins();
         return new ResponseEntity<>(adminDtoList, HttpStatus.OK);
    }

    @GetMapping("/getAdminById/{adminId}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable Integer adminId)
    {
        logger.info("Admin Controller: Get Admin by it's ID");
        AdminDto adminDto =  adminService.getAdminById(adminId);
        return new ResponseEntity<>(adminDto,HttpStatus.OK);
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<AdminDto> createNewAdmin(@Valid @RequestBody AdminDto adminDto)
    {
        AdminDto saveAdmin =  adminService.createNewAdmin(adminDto);
        return new ResponseEntity<>(saveAdmin,HttpStatus.OK);
    }

    @DeleteMapping("/deleteAdminById/{adminId}")
    public ResponseEntity<String> deleteAdminById(@PathVariable Integer adminId)
    {
       String msg =  adminService.deleteAdminById(adminId);
       return new ResponseEntity<>(msg,HttpStatus.OK);
    }

    @PutMapping("/updateAdminById/{adminId}")
    public ResponseEntity<AdminDto> updateAdminById(@PathVariable Integer adminId,
                                                    @Valid @RequestBody AdminDto adminDto)
    {
        AdminDto updatedAdmin =  adminService.updateAdmin(adminId,adminDto);
        return new ResponseEntity<>(updatedAdmin,HttpStatus.OK);
    }


}
