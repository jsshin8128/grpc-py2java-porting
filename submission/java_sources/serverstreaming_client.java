 package com.example;

import serverstreaming.ServerStreamingGrpc;
import serverstreaming.ServerStreamingProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ServerStreamingClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ServerStreamingGrpc.ServerStreamingBlockingStub stub = 
            ServerStreamingGrpc.newBlockingStub(channel);

        ServerStreamingProto.Number request = ServerStreamingProto.Number.newBuilder()
                .setValue(5)
                .build();

        stub.getServerResponse(request).forEachRemaining(response -> {
            System.out.println("[서버 -> 클라이언트] " + response.getMessage());
        });

        channel.shutdown();
    }
}

