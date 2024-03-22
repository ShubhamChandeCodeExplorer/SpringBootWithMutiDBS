package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import com.csi.service.CustomerServiceImpl;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @PostMapping("/savecustomer")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer){
        return ResponseEntity.ok(customerServiceImpl.save(customer));
    }

    @GetMapping("/signin/{custEmail}/{custPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String custEmail,@PathVariable String custPassword){
        return ResponseEntity.ok(customerServiceImpl.signIn(custEmail,custPassword));
    }

    @GetMapping("/findbycustid/{custId}")
    public ResponseEntity<Optional<Customer>> findByCustId(@PathVariable int custId){
        return ResponseEntity.ok(customerServiceImpl.findById(custId));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(customerServiceImpl.findAll());
    }

    @PutMapping("update/{custId}")
    public ResponseEntity<Customer> update(@PathVariable int custId,@Valid @RequestBody Customer customer){
        Customer customer1=customerServiceImpl.findById(custId).orElseThrow(()->new RecordNotFoundException("customer id dosent exist"));
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustName(customer.getCustName());
        customer1.setCustDOB(customer.getCustDOB());
        customer1.setCustContact(customer.getCustContact());
        customer1.setCustEmail(customer.getCustEmail());
        customer1.setCustPassword(customer.getCustPassword());

        return ResponseEntity.ok(customerServiceImpl.update(customer1));
    }

    @DeleteMapping("/deletebyid/{custId}")
    public ResponseEntity<String> deletebyId(@PathVariable int custId){
        customerServiceImpl.deleteById(custId);
        return ResponseEntity.ok("Customer data deleted sucessfully!!!!");
    }

}
