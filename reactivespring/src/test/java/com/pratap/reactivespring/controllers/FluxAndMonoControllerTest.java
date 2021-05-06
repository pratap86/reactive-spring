package com.pratap.reactivespring.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebFluxTest
class FluxAndMonoControllerTest {

    @Autowired
    WebTestClient webTestClient;//@WebFluxTest is responsible to create the instance of WebTestClient

    @Test
    void testReturnFlux_Approach1() {

        Flux<Integer> integerFlux = webTestClient.get().uri("/flux").accept(MediaType.APPLICATION_JSON)
                .exchange()//here end-point would be called
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(integerFlux)
                .expectSubscription()
                .expectNext(1, 2, 3, 4)
                .verifyComplete();
    }

    @Test
    void testReturnFlux_Approach2() {

        webTestClient.get().uri("/flux").accept(MediaType.APPLICATION_JSON)
                .exchange()//here end-point would be called
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Integer.class)
                .hasSize(4);
    }

    @Test
    void testReturnFlux_Approach3() {

        EntityExchangeResult<List<Integer>> entityExchangeResult = webTestClient.get().uri("/flux").accept(MediaType.APPLICATION_JSON)
                .exchange()//here end-point would be called
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Integer.class)
                .returnResult();

        assertEquals(Arrays.asList(1,2,3,4), entityExchangeResult.getResponseBody());
    }

    @Test
    void testReturnFlux_Approach4() {

        webTestClient.get().uri("/flux").accept(MediaType.APPLICATION_JSON)
                .exchange()//here end-point would be called
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Integer.class)
                .consumeWith(response -> assertEquals(Arrays.asList(1,2,3,4), response.getResponseBody()));
    }

    @Test
    void testReturnFluxStream_With_Infinite_Stream() {

        Flux<Long> longStreamFlux = webTestClient.get().uri("/flux-infinite-stream").accept(MediaType.APPLICATION_NDJSON)
                .exchange()//here end-point would be called
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody();

        StepVerifier.create(longStreamFlux)
                .expectNext(0l, 1l, 2l)
                .thenCancel()
                .verify();

    }

    @Test
    void testMono(){

        Integer expectedValue = 1;

        webTestClient.get().uri("/mono")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith((response) -> {
                    assertEquals(expectedValue, response.getResponseBody());
                });

    }
}