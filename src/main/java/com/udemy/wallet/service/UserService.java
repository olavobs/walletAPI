package com.udemy.wallet.service;

import java.util.Optional;

import com.udemy.wallet.entity.User;

public interface UserService {

	User save(User user);

	Optional<User> findByEmail(String email);
}
