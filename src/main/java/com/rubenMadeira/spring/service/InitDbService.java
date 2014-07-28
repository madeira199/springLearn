package com.rubenMadeira.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rubenMadeira.spring.entity.Blog;
import com.rubenMadeira.spring.entity.Item;
import com.rubenMadeira.spring.entity.Role;
import com.rubenMadeira.spring.entity.User;
import com.rubenMadeira.spring.repository.BlogRepository;
import com.rubenMadeira.spring.repository.ItemRepository;
import com.rubenMadeira.spring.repository.RoleRepository;
import com.rubenMadeira.spring.repository.UserRepository;

@Transactional
@Service
public class InitDbService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@PostConstruct
	public void init(){
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setEnable(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		Blog blogJavaVids = new Blog();
		blogJavaVids.setName("JavaVids");
		blogJavaVids.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		blogJavaVids.setUser(userAdmin);
		blogRepository.save(blogJavaVids);
		
		Item item1 = new Item();
		item1.setBlog(blogJavaVids);
		item1.setTitle("first");
		item1.setLink("http://www.javavids.com");
		item1.setPublishedDate(new Date());
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setBlog(blogJavaVids);
		item2.setTitle("secund");
		item2.setLink("http://www.javavids.com");
		item2.setPublishedDate(new Date());
		itemRepository.save(item2);
		
	}
}
