package com.myapp.auth_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.auth_service.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	Customer findByEmail(String email);

}
