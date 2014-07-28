package com.rubenMadeira.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubenMadeira.spring.entity.Blog;
import com.rubenMadeira.spring.entity.User;


public interface BlogRepository extends JpaRepository<Blog, Integer>{

	List<Blog> findByUser(User user);
	
}
