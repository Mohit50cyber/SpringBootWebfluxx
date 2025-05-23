package com.javareactive.SpringBootWebflux.service;

import com.javareactive.SpringBootWebflux.dao.CustomerDao;
import com.javareactive.SpringBootWebflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;

    public List<Customer> loadAllCustomer(){
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end-start));
        return customers;
    }

    public Flux<Customer> loadAllCustomerStream(){
        long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end-start));
        return customers;
    }

}
