package com.javareactive.SpringBootWebflux.handler;

import com.javareactive.SpringBootWebflux.dao.CustomerDao;
import com.javareactive.SpringBootWebflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomer(ServerRequest request){
        Flux<Customer> customersList = customerDao.getCustomersList();
        return (Mono<ServerResponse>) ServerResponse.ok().body((BodyInserter<?, ? super ServerHttpResponse>) customersList);

    }

    public Mono<ServerResponse> findCustomer(ServerRequest request){
        Integer customerId = Integer.valueOf(request.pathVariable("input"));
//        customerDao.getCustomersList().filter(c -> c.getId() == customerId)
//                .take(1).single();
        Mono<Customer> customerMono = customerDao.getCustomersList().filter(c -> c.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono,Customer.class);


    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + " : " + dto.getName());
        return ServerResponse.ok().body(saveResponse,String.class);
    }



}
