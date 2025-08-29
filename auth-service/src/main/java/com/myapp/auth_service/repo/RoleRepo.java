package com.myapp.auth_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.auth_service.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

}
