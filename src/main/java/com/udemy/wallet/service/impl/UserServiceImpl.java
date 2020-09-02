package com.udemy.wallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.wallet.entity.User;
import com.udemy.wallet.repository.UserRepository;
import com.udemy.wallet.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;
	
	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	
	
}
