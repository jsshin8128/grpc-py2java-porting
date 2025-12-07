package com.example;

import hellogrpc.MyServiceGrpc;
import hellogrpc.HelloGrpcProto;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MyServiceServer {
    private Server server;

    static class MyServiceServicerImpl extends MyServiceGrpc.MyServiceImplBase {
        @Override
        public void myFunction(HelloGrpcProto.MyNumber request, 
                              StreamObserver<HelloGrpcProto.MyNumber> responseObserver) {
            int result = HelloGrpc.myFunc(request.getValue());
            
            HelloGrpcProto.MyNumber response = HelloGrpcProto.MyNumber.newBuilder()
                    .setValue(result)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new MyServiceServicerImpl())
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
        final MyServiceServer server = new MyServiceServer();
        server.start();
        server.blockUntilShutdown();
    }
}

