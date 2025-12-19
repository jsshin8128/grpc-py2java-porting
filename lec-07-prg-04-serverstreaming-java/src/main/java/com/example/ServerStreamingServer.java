package com.example;

import serverstreaming.ServerStreamingGrpc;
import serverstreaming.ServerStreamingProto;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServerStreamingServer {
    private Server server;

    static class ServerStreamingServiceImpl extends ServerStreamingGrpc.ServerStreamingImplBase {
        @Override
        public void getServerResponse(ServerStreamingProto.Number request,
                                     StreamObserver<ServerStreamingProto.Message> responseObserver) {
            
            System.out.println("서버가 server streaming 처리 중... {" + request.getValue() + "}");
            
            List<String> messages = Arrays.asList(
                "message #1",
                "message #2",
                "message #3",
                "message #4",
                "message #5"
            );
            
            for (String msg : messages) {
                ServerStreamingProto.Message message = ServerStreamingProto.Message.newBuilder()
                        .setMessage(msg)
                        .build();
                responseObserver.onNext(message);
            }
            
            responseObserver.onCompleted();
        }
    }

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new ServerStreamingServiceImpl())
                .build()
                .start();
        
        System.out.println("서버 시작됨! 포트 " + port + "에서 대기 중...");
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("서버 종료 중...");
                try {
                    if (server != null) {
                        server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final ServerStreamingServer server = new ServerStreamingServer();
        server.start();
        server.blockUntilShutdown();
    }
}

