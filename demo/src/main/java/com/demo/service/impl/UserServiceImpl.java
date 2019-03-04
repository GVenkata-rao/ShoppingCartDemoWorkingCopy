package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserRepository;
import com.demo.model.User;
import com.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userRepo.findByUsernameAndPassword(username, password);
	}

	
}
