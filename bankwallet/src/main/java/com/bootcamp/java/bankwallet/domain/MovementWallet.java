package com.bootcamp.java.bankwallet.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("MovementWallet")
public class MovementWallet {
	private String movementId;
	private Date movementDate;
	private String type;
	private double amount;
	private String walletId;

}
