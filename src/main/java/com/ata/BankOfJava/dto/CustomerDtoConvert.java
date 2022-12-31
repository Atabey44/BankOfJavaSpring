package com.ata.BankOfJava.dto;

import com.ata.BankOfJava.model.City;
import com.ata.BankOfJava.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConvert {

    public  CustomerDto convert(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setAdress(customer.getAdress());
        customerDto.setDateOfBirt(customer.getDateOfBirt());
        customerDto.setCity(City.valueOf(customer.getCity().name()));
        return customerDto;
    }

}
