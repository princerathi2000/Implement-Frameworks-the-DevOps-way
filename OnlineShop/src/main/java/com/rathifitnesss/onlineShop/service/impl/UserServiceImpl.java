package com.rathifitnesss.onlineShop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rathifitnesss.onlineShop.entity.User;
import com.rathifitnesss.onlineShop.repository.UserRepository;
import com.rathifitnesss.onlineShop.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepository = userRepo;
	}
	
	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Integer id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);	
	}



}
