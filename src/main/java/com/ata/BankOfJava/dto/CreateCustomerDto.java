package com.ata.BankOfJava.dto;

import com.ata.BankOfJava.model.City;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateCustomerDto {

    private String id;
    private String name;
    private Integer dateOfBirt;
    private City city;
    private String adress;
}
