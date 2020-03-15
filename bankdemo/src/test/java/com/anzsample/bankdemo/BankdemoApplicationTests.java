package com.anzsample.bankdemo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.anzsample.bankdemo.dao.AccountRepository;
import com.anzsample.bankdemo.model.Account;
import com.anzsample.bankdemo.model.Transaction;



@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
class BankdemoApplicationTests {

	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private AccountRepository accountRepository;
	
	@BeforeEach()
	public void setUp(){

		//Create an account object for unit testing
		
		//given
        Account account = new Account();
		
		account.setAccountName("INDSavingsINR");
		account.setAccountType("Savings");
		account.setBalanceDate(LocalDate.now().toString());
		account.setCurrency("INR");
		account.setOpeningAvailableBalance(new BigDecimal(60369.00));
		
		Transaction transaction1 = new Transaction();
		transaction1.setDebitAmount(new BigDecimal(1000.00));
		transaction1.setTransactionNarrative("Debit amount"); 
		transaction1.setTransactionType("Debit");
		transaction1.setValueDate(LocalDate.of(2020,Month.MAY,10).toString());
		transaction1.setAccount(account);
		transaction1.setCurrency(account.getCurrency());
		
		Transaction transaction2 = new Transaction();
		transaction2.setCreditAmount(new BigDecimal(5000.00));
		transaction2.setTransactionNarrative("Credit amount"); 
		transaction2.setTransactionType("Credit");
		transaction2.setValueDate(LocalDate.of(2020,Month.APRIL,12).toString());
		transaction2.setAccount(account);
		transaction2.setCurrency(account.getCurrency());
		
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction1);
	    transactions.add(transaction2);
		
		account.setTransactions(transactions);
 
		//Persist the account object
		accountRepository.save(account);
        
	}
	
	
	@Test
    public void whenFindByAccountName_thenReturnAccount() {
        log.info("... testByAccountName ...");
 
        //when
        Optional<Account> accountFound = accountRepository.findByAccountName("INDSavingsINR");
        
        // then
        assertTrue(accountFound.get().getAccountName().equals("INDSavingsINR"));
        assertEquals(new BigDecimal(60369.00), accountFound.get().getOpeningAvailableBalance());
	}
     
	
	@Test
	public void whenFindAll_thenReturnAllAccounts() {
	    
		log.info("... testByAccountSize ..."); 
		
		// when
	    List<Account> allAccounts = new ArrayList<>();
	    accountRepository.findAll().forEach(allAccounts::add);
	    
	    // then
	    assertTrue(allAccounts.size()==4);
	}

	@Test
    public void whenFindByAccountName_thenReturnAllTransactions() {
        log.info("... testByTransactionSize ...");
 
        //when
        Optional<Account> accountFound = accountRepository.findByAccountName("INDSavingsINR");
       
        // then
        assertTrue(accountFound.get().getTransactions().size()==2);
        
	}
	
	@Test
    public void whenFindByAccountName_thenReturnTransactionNarrative() {
        log.info("... testByTransactionNarrative ...");
 
        //when
        Optional<Account> accountFound = accountRepository.findByAccountName("INDSavingsINR");
        
        List<Transaction> transactions = accountFound.get().getTransactions();
        
        
        //then
        Assertions.assertAll("transactions",
            () -> assertEquals(transactions.get(0).getTransactionNarrative(), "Debit amount"),
            () -> assertEquals(transactions.get(1).getTransactionNarrative(), "Credit amount")
            
        );
        
    }
	
	@Test
    public void whenFindByAccountName_thenReturnTransactionAccount() {
        log.info("... testByTransactionAccount ...");
 
        //when
        Optional<Account> accountFound = accountRepository.findByAccountName("INDSavingsINR");
        
        List<Transaction> transactions = accountFound.get().getTransactions();
        
        //then
        assertTrue(transactions.get(0).getAccount().getAccountName().equals("INDSavingsINR"));
        assertEquals("INDSavingsINR",transactions.get(1).getAccount().getAccountName());
        
    }
	
}