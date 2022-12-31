package com.ata.BankOfJava.controller;

import com.ata.BankOfJava.dto.AccountDto;
import com.ata.BankOfJava.dto.CreateAccountDto;
import com.ata.BankOfJava.dto.UpdateAccountDto;
import com.ata.BankOfJava.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/account")
public class AccountApi {

    private final AccountService accountService;

    public AccountApi(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
    return  ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getOneAccount(@PathVariable String id){
        return ResponseEntity.ok(accountService.getOneAccount(id));
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountDto createAccountDto){

        return ResponseEntity.ok(accountService.createAccount(createAccountDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateOneAccount
            (@PathVariable String id, @RequestBody UpdateAccountDto updateAccountDto){
        return ResponseEntity.ok(accountService.updateOneAccount(id, updateAccountDto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOneAccount(@PathVariable String id){
        accountService.deleteOneAccount(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/withDrawMoney/{id}")
    public ResponseEntity<AccountDto> withDrawMoney(@PathVariable String id, Double amount){
        return ResponseEntity.ok(accountService.withDrawMoney(id, amount));
    }

}
