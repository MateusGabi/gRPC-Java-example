package io.github.mateusgabi.grpc.helloworld;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import com.github.mateusgabi.grpc.helloworld.HelloWorldServiceGrpc;
import com.github.mateusgabi.grpc.helloworld.Person;
import com.github.mateusgabi.grpc.helloworld.Greeting;

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
