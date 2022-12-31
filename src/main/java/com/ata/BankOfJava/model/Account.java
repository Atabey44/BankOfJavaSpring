package com.ata.BankOfJava.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    @Id
    private String id;

    private String customerId;
    private double balance;
    private City city;
    private Currency currency;

}
