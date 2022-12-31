package com.ata.BankOfJava.controller;

import com.ata.BankOfJava.dto.CreateCustomerDto;
import com.ata.BankOfJava.dto.CustomerDto;
import com.ata.BankOfJava.dto.UpdateCustomerDto;
import com.ata.BankOfJava.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerApi {

    private final CustomerService customerService;

    public CustomerApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerDto createCustomerDto){

        return ResponseEntity.ok(customerService.createCustomer(createCustomerDto));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getOneCustomerById(@PathVariable String id){
            return ResponseEntity.ok(customerService.getOneCustomerById(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer
            (@PathVariable String id, @RequestBody UpdateCustomerDto updateCustomerDto){
        return ResponseEntity.ok(customerService.updateCustomer(id, updateCustomerDto));
    }

}
