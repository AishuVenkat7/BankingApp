package com.project.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
//@AllArgsConstructor
//public class AccountDto {
//	
//	private Long id;
//	private String accountHolderName;
//	private double balance;
//	private String emailId;
//
//}

//these fields are implicitly final
public record AccountDto(Long id, String accountHolderName,
		double balance, String emailId) {} 