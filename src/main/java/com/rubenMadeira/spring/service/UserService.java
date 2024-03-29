package com.rubenMadeira.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rubenMadeira.spring.entity.Blog;
import com.rubenMadeira.spring.entity.Item;
import com.rubenMadeira.spring.entity.User;
import com.rubenMadeira.spring.entity.Role;
import com.rubenMadeira.spring.repository.BlogRepository;
import com.rubenMadeira.spring.repository.ItemRepository;
import com.rubenMadeira.spring.repository.RoleRepository;
import com.rubenMadeira.spring.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> findAll()
	{
		return userRepository.findAll();
	}

	public User findOne(int id) {		
		return userRepository.findOne(id);
	}
	
	public User findOneUsername(String username) {		
		return userRepository.findByName(username);
	}
	
	public User findOneEmail(String email) {		
		return userRepository.findByEmail(email);
	}

	@Transactional
	public User findOneWithBlogs(int id) {
		
		User user = findOne(id);
		
		List<Blog> blogs = blogRepository.findByUser(user);
		
		for(Blog blog:blogs)
		{
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC, "description"));
			blog.setItems(items);
		}
		
		user.setBlogs(blogs);
		return user;
	}

	public void save(User user) {
		user.setEnable(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		userRepository.save(user);
		
	}
	
	public User findOneWithBlogs(String name){
		User user = userRepository.findByName(name);
		return findOneWithBlogs(user.getId());
	}

	public void delete(int id) {
		userRepository.delete(id);
	}

}
