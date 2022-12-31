package com.ata.BankOfJava.repository;

import com.ata.BankOfJava.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {


}
