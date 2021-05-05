# Reactive-Spring
This repository has the code to implement the reactive programming with spring

#### Usefull links
|urls||
|---|---|
|`https://projectreactor.io/docs`|Documentation|

#### Reactive Libraries
  - What is a Reactive Library?
    - Implementation of Reactive Stream Specification.
      - Publisher
      - Subscriber
      - Subscription
      - Processor
    - Reactive Library present today are;
      - RxJava
      - Reactor
      - Flow Class - JDK 9

#### Reactor or Project Reactor
  - Built and maintained by Pivotal
  - Recommended Library to work with Spring Boot.

#### Project Reactor Modules
|Modules|Description|
|---|---|
|`Reactor Core`|A Reactive Streams foundation for Java 8|
|`Reactor Test`|Test utilities|
|`Reactor Netty`|HTTP, TCP, UDP Clients/Servers using Netty|

#### Back Pressure
- What is Back Pressure in reactive data stream?
  - Subscriber controls the data flow from the Publisher
  - Subscriber just made the request to Publisher to just send only requested items only

#### Spring Reactive Annotations
|`Annotations`|Description|
|---|---|
|`@WebFluxTest`|is responsible to create the instance of WebTestClient|
