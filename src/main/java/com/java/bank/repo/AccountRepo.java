package com.java.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.bank.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {

//	@Query("SELECT * FROM ACCOUNT WHERE  anumber ")
//	Account findByAccountNumber(@Param("anumber") Long anumber);
//    

}
