package com.udemy.wallet.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.udemy.wallet.entity.Wallet;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class WalletDto {

	private Long id;

	@NotNull(message = "Insira um valor para a carteira")
	private BigDecimal value;

	@NotNull
	@Length(min = 3, max = 50, message = "Nome deve conter entre 3 e 50 caracteres")
	private String name;

	public Wallet convertDtoToEntity() {
		Wallet wallet = new Wallet();

		wallet.setId(this.id);
		wallet.setName(this.name);
		wallet.setValue(this.value);
		return wallet;
	}

	public WalletDto(@NotNull Wallet wallet) {
		this.id = wallet.getId();
		this.name = wallet.getName();
		this.value = wallet.getValue();
	}

}
