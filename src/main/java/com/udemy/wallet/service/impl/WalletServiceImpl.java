package com.udemy.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.wallet.entity.Wallet;
import com.udemy.wallet.repository.WalletRepository;
import com.udemy.wallet.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService{

	@Autowired
	WalletRepository repository;
	
	@Override
	public Wallet save(Wallet wallet) {
		return repository.save(wallet);
	}


	
	
}
