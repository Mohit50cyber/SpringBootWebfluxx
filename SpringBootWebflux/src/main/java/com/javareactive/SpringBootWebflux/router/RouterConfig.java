package com.javareactive.SpringBootWebflux.router;

import com.javareactive.SpringBootWebflux.handler.CustomStreamHandler;
import com.javareactive.SpringBootWebflux.handler.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomStreamHandler customStreamHandler;


    @Bean
    public RouterFunction<ServerResponse> routerFunction(CustomerHandler handler) {
        return RouterFunctions.route()
                .GET("/router/customers", request -> (Mono<ServerResponse>) handler.loadCustomer(request).block())  // or handler::loadCustomer
                .GET("router/customers/stream",customStreamHandler::getCustomers)
                .GET("/router/customers/{input}",handler::findCustomer)
                .POST("/router/customers/save",handler::saveCustomer)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> niggaman(CustomerHandler handler) {
        return RouterFunctions.route()
                .GET("/router/customers", request -> (Mono<ServerResponse>) handler.loadCustomer(request).block())  // or handler::loadCustomer
                .GET("router/customers/stream",customStreamHandler::getCustomers)
                .GET("/router/customers/{input}",handler::findCustomer)
                .POST("/router/customers/save",handler::saveCustomer)
                .build();
    }

}
