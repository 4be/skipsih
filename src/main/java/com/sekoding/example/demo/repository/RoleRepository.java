package com.sekoding.example.demo.repository;

import com.sekoding.example.demo.model.entity.ERole;
import com.sekoding.example.demo.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRolename(ERole nameRole);
}
