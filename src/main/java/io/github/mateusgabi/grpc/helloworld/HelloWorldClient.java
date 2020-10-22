package io.github.mateusgabi.grpc.helloworld;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;
import com.github.mateusgabi.grpc.helloworld.HelloWorldServiceGrpc;
import com.github.mateusgabi.grpc.helloworld.Person;
import com.github.mateusgabi.grpc.helloworld.Greeting;
import javax.annotation.PostConstruct;

@Component
public class HelloWorldClient {
    private HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();

        helloWorldServiceBlockingStub = HelloWorldServiceGrpc.newBlockingStub(managedChannel);
    }

    public String sayHello(String firstName, String lastName) {
        Person person = Person.newBuilder().setFirstName(firstName).setLastName(lastName).build();

        System.out.println("Person: " + person);

        Greeting greeting = helloWorldServiceBlockingStub.sayHello(person);

        return greeting.getMessage();
    }
}
