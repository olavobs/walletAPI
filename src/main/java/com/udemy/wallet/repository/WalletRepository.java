package com.udemy.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.wallet.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{

}
