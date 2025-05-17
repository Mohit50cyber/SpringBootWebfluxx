package com.javareactive.SpringBootWebflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class MonoFluxTest {

    @Test
    public void testMono(){
        Mono<?> monoString = Mono.just("Mohit")
                .then(Mono.error(new RuntimeException("Exception occurred")))
                .log();
        monoString.subscribe(System.out::println,(e)-> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Hibernate","microservice")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception occurred in flux")))
                .concatWithValues("Java")
                .log();
        fluxString.subscribe(System.out::println);
    }

}
