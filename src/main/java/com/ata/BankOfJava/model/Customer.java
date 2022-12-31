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
public class Customer {

    @Id
    private String id;
    private String name;
    private Integer dateOfBirt;
    private City city;
    private String adress;
}
