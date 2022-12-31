package com.ata.BankOfJava.repository;


import com.ata.BankOfJava.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
