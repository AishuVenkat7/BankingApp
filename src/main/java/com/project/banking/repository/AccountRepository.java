package com.project.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.banking.entity.Account;



@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account findByEmailId(String emailId);
	
	List<Account> findByAccountHolderNameAndId(String accountHolderName, Long id);
	
	List<Account> findByAccountHolderName(String accountHolderName);

}
