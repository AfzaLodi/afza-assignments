package com.anzsample.bankdemo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.anzsample.bankdemo.model.Account;
import com.anzsample.bankdemo.model.Transaction;
import com.anzsample.bankdemo.services.AccountService;

/**
 * @author afzalodi
 *
 */

@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	/** Mapping to get all the accounts from the database table
	 *  @param model
	 *  @return accounts to be rendered on accounts.html view
	 */
	@RequestMapping("/getAllAccounts")
	public String getAllAccounts(Model model) {
		
		Iterable<Account> allAccounts = accountService.getAllAccounts();
		model.addAttribute("allAccounts",allAccounts);
		
		return "accounts";
	}
	
	/** Mapping to get all the transactions associated to a selected account
	 *  @param accountNumber , model
	 *  @return transactions to be rendered on transactions.html view
	 */
	
	@RequestMapping("/getAllTransactionsForAccount")
	public String getAllTransactionsForAccount(@RequestParam("accountNumber") long accountNumber, Model model) {
		
		List<Transaction> allTransactions = accountService.getAllTransactionsForAccount(accountNumber);
		model.addAttribute("allTransactions",allTransactions);
		
		return "transactions";
	}

}
