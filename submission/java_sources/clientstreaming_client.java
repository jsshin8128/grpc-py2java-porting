package com.example;

import clientstreaming.ClientStreamingGrpc;
import clientstreaming.ClientStreamingProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ClientStreamingClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ClientStreamingGrpc.ClientStreamingStub stub = ClientStreamingGrpc.newStub(channel);

        CountDownLatch finishLatch = new CountDownLatch(1);

        StreamObserver<ClientStreamingProto.Message> requestObserver = 
            stub.getServerResponse(new StreamObserver<ClientStreamingProto.Number>() {
                @Override
                public void onNext(ClientStreamingProto.Number number) {
                    System.out.println("[서버 -> 클라이언트] " + number.getValue());
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("에러: " + t.getMessage());
                    finishLatch.countDown();
                }

                @Override
                public void onCompleted() {
                    finishLatch.countDown();
                }
            });

        List<String> messages = Arrays.asList(
            "message #1",
            "message #2",
            "message #3",
            "message #4",
            "message #5"
        );

        try {
            for (String msg : messages) {
                System.out.println("[클라이언트 -> 서버] " + msg);
                ClientStreamingProto.Message message = ClientStreamingProto.Message.newBuilder()
                        .setMessage(msg)
                        .build();
                requestObserver.onNext(message);
            }
        } catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }

        requestObserver.onCompleted();
        finishLatch.await(1, TimeUnit.MINUTES);
        channel.shutdown();
    }
}

