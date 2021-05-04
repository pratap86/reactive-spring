package com.pratap.reactivespring.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class FluxAndMonoCombineTest {

    @Test
    void testCombineUsingMerge(){

        Flux<String> fluxOne = Flux.just("A", "B", "C");
        Flux<String> fluxTwo = Flux.just("D", "E", "F");

        Flux<String> stringMergeFlux = Flux.merge(fluxOne, fluxTwo);

        StepVerifier.create(stringMergeFlux.log())
                .expectSubscription()
                .expectNext("A","B","C","D","E","F")
                .verifyComplete();
    }

    @Test
    void testCombineUsingMerge_With_Delay(){

        Flux<String> fluxOne = Flux.just("A", "B", "C").delayElements(Duration.ofSeconds(1));
        Flux<String> fluxTwo = Flux.just("D", "E", "F").delayElements(Duration.ofSeconds(1));

        Flux<String> stringMergeFlux = Flux.merge(fluxOne, fluxTwo);

        StepVerifier.create(stringMergeFlux.log())
                .expectSubscription()
//                .expectNext("A","B","C","D","E","F")
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    void testCombineUsingConcat(){

        Flux<String> fluxOne = Flux.just("A", "B", "C");
        Flux<String> fluxTwo = Flux.just("D", "E", "F");

        Flux<String> stringMergeFlux = Flux.concat(fluxOne, fluxTwo);

        StepVerifier.create(stringMergeFlux.log())
                .expectSubscription()
                .expectNext("A","B","C","D","E","F")
                .verifyComplete();
    }

    @Test
    void testCombineUsingConcat_With_Delay(){

        Flux<String> fluxOne = Flux.just("A", "B", "C").delayElements(Duration.ofSeconds(1));
        Flux<String> fluxTwo = Flux.just("D", "E", "F").delayElements(Duration.ofSeconds(1));

        Flux<String> stringMergeFlux = Flux.concat(fluxOne, fluxTwo);

        StepVerifier.create(stringMergeFlux.log())
                .expectSubscription()
                .expectNext("A","B","C","D","E","F")
                .verifyComplete();
    }

    @Test
    void testCombineUsingZip(){

        Flux<String> fluxOne = Flux.just("A", "B", "C");
        Flux<String> fluxTwo = Flux.just("D", "E", "F");

        // AD, BE, CF
        Flux<String> stringMergeFlux = Flux.zip(fluxOne, fluxTwo, String::concat);

        StepVerifier.create(stringMergeFlux.log())
                .expectSubscription()
                .expectNext("AD", "BE", "CF")
                .verifyComplete();
    }
}
