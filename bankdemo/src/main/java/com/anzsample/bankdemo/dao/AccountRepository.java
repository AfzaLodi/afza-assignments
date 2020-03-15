package com.anzsample.bankdemo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.anzsample.bankdemo.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>{
	
	Optional<Account> findByAccountName(String name);

}
