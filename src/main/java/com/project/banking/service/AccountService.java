package com.project.banking.service;

import java.util.List;

import org.springframework.data.domain.Page;

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

//	adding pagination
	Page<AccountDto> findPaginated(int pageNo, int pageSize);
	
//	sorting
	List<AccountDto> findSorting(String sortField, String sortDirection);
	
//	pagination + sorting
	Page<AccountDto> findPaginationAndSorting(int pageNo, int pageSize, String sortField, String sortDirection);

}
