package com.udemy.wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.wallet.dto.WalletDto;
import com.udemy.wallet.entity.Wallet;
import com.udemy.wallet.response.Response;
import com.udemy.wallet.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	@Autowired
	WalletService service;

	@PostMapping
	public ResponseEntity<Response<WalletDto>> create(@Valid @RequestBody WalletDto dto, BindingResult result) {

		Response<WalletDto> response = new Response<WalletDto>();

		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		Wallet wallet = service.save(dto.convertDtoToEntity());

		response.setData(new WalletDto(wallet));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
