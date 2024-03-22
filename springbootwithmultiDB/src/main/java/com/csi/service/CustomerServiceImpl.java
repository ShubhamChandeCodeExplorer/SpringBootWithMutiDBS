package com.csi.service;

import com.csi.model.Customer;
import com.csi.repo.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl {
    @Autowired
    private CustomerRepository customerRepositoryImpl;

    public Customer save(Customer customer){
        return customerRepositoryImpl.save(customer);
    }

    public boolean signIn(String custEmail,String custPassword){
        boolean flag=false;
        Customer customer=customerRepositoryImpl.findByCustEmailAndCustPassword(custEmail,custPassword);
        if(customer!=null&&customer.getCustEmail().equals(custEmail)&&customer.getCustPassword().equals(custPassword)){
            flag=true;
        }
        return flag;
    }

    public Optional<Customer> findById(int custId){
        return customerRepositoryImpl.findById(custId);
    }

    public List<Customer> findAll(){
        return customerRepositoryImpl.findAll();
    }

    public Customer update(Customer customer){
        return customerRepositoryImpl.save(customer);
    }

    public void deleteById(int custId){
        customerRepositoryImpl.deleteById(custId);
    }

}
