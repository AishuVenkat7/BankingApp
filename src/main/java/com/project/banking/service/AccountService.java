package com.project.banking.service;

import java.util.List;

import com.project.banking.dto.AccountDto;

public interface AccountService {
	
	List<AccountDto> getAllAccounts();
	
	AccountDto getAccountById(Long id);
	
	List<AccountDto> getAccountByAccountHolderNameAndId(String accountHolderName, Long id);
	
	AccountDto getAccountByEmailId(String emailId);
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto deposit(Long id, double amount);
	
	AccountDto withdraw(Long id, double amount);
	
	void deleteAccount(Long id);

}
