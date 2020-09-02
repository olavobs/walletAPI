package com.udemy.wallet.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.udemy.wallet.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	private Long id;

	@Email(message = "Email inválido")
	private String email;

	@Length(min = 3, max = 50, message = "Nome deve conter entre 3 e 50 caracteres")
	private String name;

	@NotNull
	@Length(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
	private String password;

	public User convertDtoToEntity() {
		User u = new User();

		u.setId(this.id);
		u.setEmail(this.email);
		u.setName(this.name);
		u.setPassword(this.password);

		return u;
	}

	public UserDto(@NotNull User u) {
		this.email = u.getEmail();
		this.id = u.getId();
		this.name = u.getName();
		this.password = u.getPassword();
	}

}
