package com.example;

import bidirectional.BidirectionalGrpc;
import bidirectional.BidirectionalOuterClass;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BidirectionalServer {
    private Server server;

    static class BidirectionalServiceImpl extends BidirectionalGrpc.BidirectionalImplBase {
        @Override
        public StreamObserver<BidirectionalOuterClass.Message> getServerResponse(
                StreamObserver<BidirectionalOuterClass.Message> responseObserver) {
            
            System.out.println("서버가 bidirectional streaming 처리 중...");
            
            return new StreamObserver<BidirectionalOuterClass.Message>() {
                @Override
                public void onNext(BidirectionalOuterClass.Message message) {
                    responseObserver.onNext(message);
                }

                @Override
                public void onError(Throwable t) {
                    responseObserver.onError(t);
                }

                @Override
                public void onCompleted() {
                    responseObserver.onCompleted();
                }
            };
        }
    }

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new BidirectionalServiceImpl())
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
        final BidirectionalServer server = new BidirectionalServer();
        server.start();
        server.blockUntilShutdown();
    }
}

