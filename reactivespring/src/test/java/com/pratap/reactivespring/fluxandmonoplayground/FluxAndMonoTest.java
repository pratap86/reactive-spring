package com.pratap.reactivespring.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    public void testFlux(){

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                                    .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                                    .concatWith(Flux.just("After Error"))
                                    .log();

        stringFlux
                .subscribe(System.out::println,
                        e -> System.err.println("Exception is :"+e),
                        () -> System.out.println("Completed"));
    }

    @Test
    public void testFluxElements_WithoutFail(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring").log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .verifyComplete();
    }

    @Test
    public void testFluxElements_WithError(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                //.expectError(RuntimeException.class)
                .expectErrorMessage("Exception Occurred")
                .verify();
    }

    @Test
    public void testFluxElementsCount_WithError(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectErrorMessage("Exception Occurred")
                .verify();
    }

    @Test
    public void testFluxElements_WithError_AnotherWay(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring", "Spring Boot", "Reactive Spring")
                .expectErrorMessage("Exception Occurred")
                .verify();
    }

    @Test
    public void testMono_WithoutError(){
        Mono<String> stringMono = Mono.just("Reactive Spring");
        StepVerifier.create(stringMono.log())
                .expectNext("Reactive Spring")
                .verifyComplete();
    }

    @Test
    public void testMono_WithError(){
        StepVerifier.create(Mono.error(new RuntimeException("Exception Occurred")).log())
                .expectErrorMessage("Exception Occurred")
                .verify();
    }
}
