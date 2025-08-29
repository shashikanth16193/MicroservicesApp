package com.myapp.auth_service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.myapp.auth_service.entity.Customer;
import com.myapp.auth_service.entity.Role;
import com.myapp.auth_service.repo.CustomerRepo;
import com.myapp.auth_service.repo.RoleRepo;

@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	CustomerRepo customerRepo;
	

	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	  Role admin=roleRepo.save(new Role("ADMIN"));
	  Role teacher=roleRepo.save(new Role("TEACHER"));
	  Role student=roleRepo.save(new Role("STUDENT"));
		
	    customerRepo.save(new Customer("admin@gmail.com",encoder.encode("admin123"),admin));
	    customerRepo.save(new Customer("student@gmail.com",encoder.encode("student123"),student));
	    customerRepo.save(new Customer("teacher@gmail.com",encoder.encode("teacher123"),teacher));
	    
	}

}
