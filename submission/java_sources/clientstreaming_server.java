package com.example;

import clientstreaming.ClientStreamingGrpc;
import clientstreaming.ClientStreamingProto;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ClientStreamingServer {
    private Server server;

    static class ClientStreamingServiceImpl extends ClientStreamingGrpc.ClientStreamingImplBase {
        @Override
        public StreamObserver<ClientStreamingProto.Message> getServerResponse(
                StreamObserver<ClientStreamingProto.Number> responseObserver) {
            
            System.out.println("서버가 client streaming 처리 중...");
            
            return new StreamObserver<ClientStreamingProto.Message>() {
                int count = 0;

                @Override
                public void onNext(ClientStreamingProto.Message message) {
                    count++;
                }

                @Override
                public void onError(Throwable t) {
                    responseObserver.onError(t);
                }

                @Override
                public void onCompleted() {
                    ClientStreamingProto.Number response = ClientStreamingProto.Number.newBuilder()
                            .setValue(count)
                            .build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            };
        }
    }

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new ClientStreamingServiceImpl())
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
        final ClientStreamingServer server = new ClientStreamingServer();
        server.start();
        server.blockUntilShutdown();
    }
}

