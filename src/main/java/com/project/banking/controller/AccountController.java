package com.project.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.banking.dto.AccountDto;
import com.project.banking.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<AccountDto>> getAllAccounts() {
		List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}

	@GetMapping("/email/{emailId}")
	public ResponseEntity<AccountDto> getAccountByEmailId(@PathVariable String emailId) {
		AccountDto accountDto = accountService.getAccountByEmailId(emailId);
		return ResponseEntity.ok(accountDto);
	}
	
	//query parameter -- @RequestParam
	@GetMapping("/holdername/{name}")
	public ResponseEntity<List<AccountDto>> getAccountByAccountHolderNameAndId(@PathVariable String name,
			@RequestParam(required = false) Long id) {
		List<AccountDto> accounts = accountService.getAccountByAccountHolderNameAndId(name, id);
		return ResponseEntity.ok(accounts);
	}

	@PostMapping("/add-account")
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {

		AccountDto createdAccount = accountService.createAccount(accountDto);
		return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
	}

	@PutMapping("/deposit/{id}")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		AccountDto depositedAccount = accountService.deposit(id, request.get("amount"));
		return ResponseEntity.ok(depositedAccount);
	}

	@PutMapping("/withdraw/{id}")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		AccountDto withdrawAccount = accountService.withdraw(id, request.get("amount"));
		return ResponseEntity.ok(withdrawAccount);
	}

	@DeleteMapping("/id/{id}")
	public String DeleteAccountById(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return "Account is Deleted Successfully";
	}

}
