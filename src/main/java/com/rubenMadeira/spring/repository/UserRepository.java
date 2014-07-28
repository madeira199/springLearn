package com.rubenMadeira.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubenMadeira.spring.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String name);

	User findByEmail(String email);

}
