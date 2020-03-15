package com.anzsample.bankdemo.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anzsample.bankdemo.dao.AccountRepository;
import com.anzsample.bankdemo.model.Account;
import com.anzsample.bankdemo.model.Transaction;


/**
 * @author afzalodi
 *
 */

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	/** Method to get all the accounts from the ACCOUNT table
	 *  @param 
	 *  @return all accounts
	 */
	
	public Iterable<Account> getAllAccounts() {
		Iterable<Account> allAccounts = accountRepository.findAll();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		for (Account account : allAccounts) {
			account.setBalanceDate(formatter.format(LocalDate.parse(account.getBalanceDate())));
		}
		
		return allAccounts;
	}
	
	/** Method to get all the transactions associated to a selected account
	 *  @param accountNumber
	 *  @return transactions from the TRANSACTION table
	 */
	public List<Transaction> getAllTransactionsForAccount(long accountNumber) {
		Optional<Account> account = accountRepository.findById(accountNumber);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
		
		account.get().getTransactions().stream().forEach(t -> t.setValueDate(formatter.format(LocalDate.parse(t.getValueDate()))));
		
		
		return account.get().getTransactions();
	}
}
