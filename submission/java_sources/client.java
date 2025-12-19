package com.example;

import hellogrpc.MyServiceGrpc;
import hellogrpc.HelloGrpcProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MyServiceClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        MyServiceGrpc.MyServiceBlockingStub stub = MyServiceGrpc.newBlockingStub(channel);

        HelloGrpcProto.MyNumber request = HelloGrpcProto.MyNumber.newBuilder()
                .setValue(4)
                .build();

        HelloGrpcProto.MyNumber response = stub.myFunction(request);

        System.out.println("결과: " + response.getValue());

        channel.shutdown();
    }
}
