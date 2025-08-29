package com.myapp.auth_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.auth_service.cconfig.JwtUtil;
import com.myapp.auth_service.entity.Customer;
import com.myapp.auth_service.entity.Role;
import com.myapp.auth_service.repo.CustomerRepo;
import com.myapp.auth_service.repo.RoleRepo;

@RestController
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/myLogin")
	String myLogin(@RequestBody Map<String,Object> authRequest) {
		
		 UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(authRequest.get("email"), authRequest.get("password"));
		String jwtToken=null;
		 Authentication authObj= authenticationManager.authenticate(authToken);
		 if(authObj.isAuthenticated()) {
			 Customer customer = customerRepo.findByEmail(authObj.getName());
		   jwtToken= jwtUtil.genearteJwt(authObj.getName(),authObj.getAuthorities(),customer.getId());
		 }
		 
		 return jwtToken;
	}
	
	@PostMapping("/register")
	ResponseEntity<Customer> register(@RequestBody Map<String,Object> details)
	{
		String email = (String) details.get("email");
	    String rawPassword = (String) details.get("password");
	    
	    String encodedPassword = encoder.encode(rawPassword);
	    
	    String roles=(String)details.get("roles");
		
		return ResponseEntity.status(HttpStatus.OK).body(customerRepo.save(new Customer(email,encodedPassword,roleRepo.save(new Role(roles)))));
	
	}
	
	
	

}
