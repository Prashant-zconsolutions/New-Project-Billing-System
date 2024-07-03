package com.mcb.billing;

import com.mcb.billing.dto.BillDto;
import com.mcb.billing.entity.Bill;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class ElectricityBillingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectricityBillingSystemApplication.class, args);
	}


}
