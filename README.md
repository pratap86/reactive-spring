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
#### Netty
- A non-blocking server
- An asynchronous event-driven application framework for rapid development of maintainable high performance protocol servers & clients.
- Built top of Java
- Used by Facebook, Google,  Instagram, Twitter, Apache Cassandra etc
- Protocols Supported FTP, HTTP, SMTP, WebSocket and etc

#### Events in Netty
- Client requesting a new connection is treated as an event
- Client requesting for data is treated as an event
- Client posted for data is treated as an event
- Errors are treated as an event

#### Netty Channel
- Channel represents the connection between the client and server

#### Netty Channel + Events
- Events processed through channel
- Events from Client to Server aka Inbound Events
  - Requesting for data
  - Posting data
- Events from Server to Client aks Outbound Events
  - Opening or Closing a connection
  - Sending response to the Client

#### Event Loop
- Continuously Looks for events from Event Queue, picked them one by one and processed further, if No event in Event Queue, wait...
- EventLoop is registered with a single dedicated thread

#### Channel Life Cycle
- channel is created(1) -> registered with EventLoop(2) -> Channel is Active(3) -> Channel is InActive(4) -> Channel Unregistered(5)
- (2) Needed for event loop to forward the events to the right channel in the future
- (3) Client and server are connected. Ready to send and recieve events.
- (4) Client and Server are not connected
- (5) Channel unregistered from the eventloop
