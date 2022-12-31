package com.ata.BankOfJava.dto;

import com.ata.BankOfJava.model.Account;
import com.ata.BankOfJava.model.City;
import com.ata.BankOfJava.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {


    public AccountDto convert(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setCity(City.valueOf(account.getCity().name()));
        accountDto.setBalance(account.getBalance());
        accountDto.setCurrency(account.getCurrency());
        accountDto.setCustomerId(account.getCustomerId());
        return accountDto;
    }
}
