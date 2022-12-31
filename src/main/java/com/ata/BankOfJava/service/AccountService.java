package com.ata.BankOfJava.service;

import com.ata.BankOfJava.dto.AccountDto;
import com.ata.BankOfJava.dto.AccountDtoConverter;
import com.ata.BankOfJava.dto.CreateAccountDto;
import com.ata.BankOfJava.dto.UpdateAccountDto;
import com.ata.BankOfJava.model.Account;
import com.ata.BankOfJava.model.Customer;
import com.ata.BankOfJava.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountDtoConverter accountDtoConverter;
    private final CustomerService customerService;

    public AccountService(AccountRepository accountRepository, AccountDtoConverter accountDtoConverter, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.accountDtoConverter = accountDtoConverter;
        this.customerService = customerService;
    }

    public List<AccountDto> getAllAccounts() {
        List<Account> accountList = accountRepository.findAll();

        return accountList.stream().map(accountDtoConverter::convert).collect(Collectors.toList());
    }

    public AccountDto getOneAccount(String id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        return accountRepository.findById(id).map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    public AccountDto createAccount(CreateAccountDto createAccountDto) {
        Customer customer = customerService.getCustomerId(createAccountDto.getId());
        if (customer.getId()== null || customer.getId() == ""){
            return AccountDto.builder().build();
        }
        Account account = Account.builder()
                .id(createAccountDto.getId())
                .balance(createAccountDto.getBalance())
                .currency(createAccountDto.getCurrency())
                .city(createAccountDto.getCity())
                .build();
        return accountDtoConverter.convert(accountRepository.save(account));
        }

    public AccountDto updateOneAccount(String id, UpdateAccountDto updateAccountDto) {
        Customer customer = customerService.getCustomerId(id);
        if (customer.getId()== null || customer.getId() ==""){
            return AccountDto.builder().build();
        }
        Optional<Account> accountOptional= accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            account.setBalance(updateAccountDto.getBalance());
            account.setCurrency(updateAccountDto.getCurrency());
            account.setCustomerId(updateAccountDto.getCustomerId());

            accountRepository.save(account);
           });
        return accountOptional.map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    public void deleteOneAccount(String id) {
       accountRepository.deleteById(id);
    }

    public AccountDto withDrawMoney(String id, Double amount) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.map(account -> {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            }
            String message = "\"Insufficient funds -> \" +\n" +
                    "                        \"accountId : \" + id\n" +
                    "                        + \" balance : \" + account.getBalance()\n" +
                    "                        + \" amount : \" + amount";

            return message;
        });
            return accountOptional.map(accountDtoConverter::convert).orElse(new AccountDto());
     }



    }

