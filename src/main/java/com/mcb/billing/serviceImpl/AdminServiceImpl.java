package com.mcb.billing.serviceImpl;

import com.mcb.billing.dto.AdminDto;
import com.mcb.billing.ecxception.ResourceNotFoundException;
import com.mcb.billing.entity.Admin;
import com.mcb.billing.repository.AdminRepository;
import com.mcb.billing.service.AdminService;
import com.mcb.billing.utils.AdminConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {



    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<AdminDto> getAllAdmins() {

        List<Admin> adminList = adminRepository.getAllAdmins();
        return adminList.stream().map(admin -> modelMapper.map(admin, AdminDto.class)).collect(Collectors.toList());
//
//        List<AdminDto> adminDtoList = adminList.stream()
//                .map(AdminConverter::convertToAdminDto)
//                .collect(Collectors.toList());
//
//        return adminDtoList;
    }

    @Override
    public AdminDto getAdminById(Integer adminId) {

        Admin admin = adminRepository.getAdminById(adminId);

        if(admin != null)
            return AdminConverter.convertToAdminDto(admin);
        else
            throw  new ResourceNotFoundException("Admin is not exist with given Admin Id : " + adminId);
    }

    @Override
    public AdminDto createNewAdmin(AdminDto adminDto) {

        Admin admin = adminRepository.getAdminByUsername(adminDto.getAdminUserName());

//         if(admin != null && adminDto!= null && admin.getAdminUserName().equals(adminDto.getAdminUserName()))
//        {
//            System.out.println("True");
//        }

        if(admin == null){
            Admin oldAdmin = AdminConverter.convertToAdminEntity(adminDto);
            Admin saveAdmin = adminRepository.save(oldAdmin);
            return AdminConverter.convertToAdminDto(saveAdmin);
        }
        else {
            throw new ResourceNotFoundException("Admin is already exist with given Admin username : " + adminDto.getAdminUserName());

        }



    }

    @Override
    public Boolean deleteAdminById(Integer adminNumber) {

        Admin admin = adminRepository.getAdminById(adminNumber);
        if(admin == null)
        {
            return false;
        }
        else
        {
            adminRepository.deleteByAdminId(adminNumber);
            return true;
        }
    }

    @Override
    public AdminDto updateAdmin(Integer adminId, AdminDto adminDto) {

        Admin admin = adminRepository.getAdminById(adminId);
        Admin adminByUsername = adminRepository.getAdminByUsername(adminDto.getAdminUserName());
        if (adminByUsername != null) {
            throw new ResourceNotFoundException("Admin is already exist with given Admin username : " + adminByUsername.getAdminUserName());
        } else if (admin != null) {
            admin.setAdminUserName(adminDto.getAdminUserName());
            admin.setAdminPassword(adminDto.getAdminPassword());
            Admin updateAdmin = adminRepository.save(admin);
            return AdminConverter.convertToAdminDto(updateAdmin);
        } else {
            throw new ResourceNotFoundException("Admin is not exist with given Admin Id : " + adminId);
        }
    }

}
