package com.myapp.auth_service.cconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.myapp.auth_service.repo.CustomerRepo;

@Component
public class CustomerUDS implements UserDetailsService{

	@Autowired
	CustomerRepo customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return customerRepo.findByEmail(email);
	}

}
