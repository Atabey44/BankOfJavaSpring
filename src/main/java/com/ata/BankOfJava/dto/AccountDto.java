package com.ata.BankOfJava.dto;


import com.ata.BankOfJava.model.City;
import com.ata.BankOfJava.model.Currency;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountDto {

    private String id;

    private String customerId;
    private double balance;
    private City city;
    private Currency currency;
}
