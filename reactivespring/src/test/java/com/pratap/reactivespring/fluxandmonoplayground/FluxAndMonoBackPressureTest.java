package com.pratap.reactivespring.fluxandmonoplayground;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoBackPressureTest {

    @Test
    void testBackPressure(){

        Flux<Integer> finiteFlux = Flux.range(1, 10).log();

        StepVerifier.create(finiteFlux)
                .expectSubscription()
                .thenRequest(1)//instruct the flux, just emits only 1 value
                .expectNext(1)
                .thenRequest(1)
                .expectNext(2)
                .thenCancel()
                .verify();
    }

    @Test
    void testCustomizeBackPressure(){

        Flux<Integer> finiteFlux = Flux.range(1, 10).log();

        finiteFlux.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnNext(Integer value) {
                request(1);
                System.out.println("value received from Flux is :"+value);
                if (value == 4){
                    cancel();
                }
            }
        });
    }
}
