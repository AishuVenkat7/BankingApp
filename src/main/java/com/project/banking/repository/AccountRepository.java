package com.project.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.banking.entity.Account;
import java.util.List;


public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account findByEmailId(String emailId);
	
	List<Account> findByAccountHolderNameAndId(String accountHolderName, Long id);
	
	List<Account> findByAccountHolderName(String accountHolderName);

}
