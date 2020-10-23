obs: this project has a scientific purpose

## What did I do?

### Install protobuf compiler

Documentation: https://github.com/protocolbuffers/protobuf

You can install on your machine or install on each application (as a dependency): https://github.com/protocolbuffers/protobuf#protobuf-runtime-installation

### 1. Create a proto file;

```proto
syntax = "proto3";

option java_multiple_files = true;

package com.github.mateusgabi.grpc.helloworld;

message Person {
    string first_name = 1;
    string last_name = 2;
}

message Greeting {
    string message = 1;
}

service HelloWorldService {
    rpc sayHello (Person) returns (Greeting);
}
```

### 2. Run proto compiler

As I installed as a project dependency:
`mvn compile`

Some files in the target path are created:
- Greeting
- GreetingOrBuilder
- HelloWorld
- HelloWorldServiceGrpc
- Person
- PersonOrBuilder

### 3. Create service implementation

```java
@GRpcService
public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {
    @Override
    public void sayHello(Person request, StreamObserver<Greeting> responseObserver) {
        System.out.println("Request: " + request);

        String message = "Hello " + request.getFirstName() + " " + request.getLastName() + "!";
        Greeting greeting = Greeting.newBuilder().setMessage(message).build();

        System.out.println("server: " + greeting);

        responseObserver.onNext(greeting);
        responseObserver.onCompleted();
    }
}
```