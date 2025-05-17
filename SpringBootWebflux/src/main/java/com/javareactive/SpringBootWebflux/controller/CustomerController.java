package com.javareactive.SpringBootWebflux.controller;

import com.javareactive.SpringBootWebflux.dto.Customer;
import com.javareactive.SpringBootWebflux.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers(){

        return customerService.loadAllCustomer();
    }

    @GetMapping(value="/stream" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomerStream(){

        return customerService.loadAllCustomerStream();
    }


}
