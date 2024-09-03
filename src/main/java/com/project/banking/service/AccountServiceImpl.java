package com.project.banking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.banking.dto.AccountDto;
import com.project.banking.entity.Account;
import com.project.banking.mapper.AccountMapper;
import com.project.banking.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream()
					   .map((account) -> AccountMapper.mapToAccountDto(account))
					   .collect(Collectors.toList());
	}

	@Override
	public AccountDto getAccountById(Long id) {
		
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}
	
	@Override
	public AccountDto getAccountByEmailId(String emailId) {
		Account account = accountRepository.findByEmailId(emailId);
		if(account ==null) {
			throw new RuntimeException("account does not exist");
		}
		return AccountMapper.mapToAccountDto(account);			
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public void deleteAccount(Long id) {
		accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("account does not exist"));
		accountRepository.deleteById(id);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("account does not exist"));
		account.setBalance(account.getBalance() + amount);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("account does not exist"));
		
		if(amount > account.getBalance()) {
			throw new RuntimeException("Insufficient amount");
		}
		
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAccountByAccountHolderNameAndId(String accountHolderName, Long id) {
		List<Account> accounts = null;
		if(id != null) {
			accounts = accountRepository.findByAccountHolderNameAndId(accountHolderName, id);
		} else {
			accounts = accountRepository.findByAccountHolderName(accountHolderName);
		}
		
		if(accounts ==null) {
			throw new RuntimeException("account does not exist");
		}
		return accounts.stream()
				   .map((account) -> AccountMapper.mapToAccountDto(account))
				   .collect(Collectors.toList());
		
	}

}
