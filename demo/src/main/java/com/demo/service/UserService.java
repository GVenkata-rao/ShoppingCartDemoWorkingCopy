package com.demo.service;

import com.demo.model.User;

public interface UserService {
	
	User findByUsernameAndPassword(String username,String password);

}
