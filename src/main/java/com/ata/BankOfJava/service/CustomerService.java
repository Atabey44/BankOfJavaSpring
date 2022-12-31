package com.ata.BankOfJava.service;

import com.ata.BankOfJava.dto.CreateCustomerDto;
import com.ata.BankOfJava.dto.CustomerDto;
import com.ata.BankOfJava.dto.CustomerDtoConvert;
import com.ata.BankOfJava.dto.UpdateCustomerDto;
import com.ata.BankOfJava.model.City;
import com.ata.BankOfJava.model.Customer;
import com.ata.BankOfJava.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConvert customerDtoConvert;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConvert customerDtoConvert) {
        this.customerRepository = customerRepository;
        this.customerDtoConvert = customerDtoConvert;
    }

    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        Customer customer = Customer.builder()
            .id(createCustomerDto.getId())
            .name(createCustomerDto.getName())
            .adress(createCustomerDto.getAdress())
            .dateOfBirt(createCustomerDto.getDateOfBirt())
            .city(City.valueOf(createCustomerDto.getCity().name()))
            .build();

        customerRepository.save(customer);
        return customerDtoConvert.convert(customerRepository.save(customer));
    }


    public List<CustomerDto> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer: customerList){
            customerDtoList.add(customerDtoConvert.convert(customer));
        }
        return customerDtoList;
    }

    public CustomerDto getOneCustomerById(String id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.map(customer -> customerDtoConvert.convert(customer)).orElse(new CustomerDto());
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);

    }

    public CustomerDto updateCustomer(String id, UpdateCustomerDto updateCustomerDto) {

        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            customer.setName(updateCustomerDto.getName());
            customer.setAdress(updateCustomerDto.getAdress());
            customer.setCity(City.valueOf(updateCustomerDto.getCity().name()));
            customer.setDateOfBirt(updateCustomerDto.getDateOfBirt());
            customerRepository.save(customer);
            return customerOptional.map(customer1 -> customerDtoConvert.convert(customer)).orElse(new CustomerDto());

        }
            return null;
        }

        protected Customer getCustomerId(String id){
        return  customerRepository.findById(id).orElse(null);
        }

    }
