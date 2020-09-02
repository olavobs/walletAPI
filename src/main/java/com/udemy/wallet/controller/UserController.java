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

import com.udemy.wallet.dto.UserDto;
import com.udemy.wallet.entity.User;
import com.udemy.wallet.response.Response;
import com.udemy.wallet.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;

	@PostMapping
	public ResponseEntity<Response<UserDto>> create(@Valid @RequestBody UserDto dto, BindingResult bind) {

		Response<UserDto> response = new Response<UserDto>();

		User user = service.save(dto.convertDtoToEntity());

		response.setData(new UserDto(user));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
