package com.rubenMadeira.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubenMadeira.spring.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

         Role findByName(String name);

}
