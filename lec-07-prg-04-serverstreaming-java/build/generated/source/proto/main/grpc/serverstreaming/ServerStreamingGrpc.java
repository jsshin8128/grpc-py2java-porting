package serverstreaming;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.0)",
    comments = "Source: serverstreaming.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ServerStreamingGrpc {

  private ServerStreamingGrpc() {}

  public static final java.lang.String SERVICE_NAME = "serverstreaming.ServerStreaming";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<serverstreaming.ServerStreamingProto.Number,
      serverstreaming.ServerStreamingProto.Message> getGetServerResponseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetServerResponse",
      requestType = serverstreaming.ServerStreamingProto.Number.class,
      responseType = serverstreaming.ServerStreamingProto.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<serverstreaming.ServerStreamingProto.Number,
      serverstreaming.ServerStreamingProto.Message> getGetServerResponseMethod() {
    io.grpc.MethodDescriptor<serverstreaming.ServerStreamingProto.Number, serverstreaming.ServerStreamingProto.Message> getGetServerResponseMethod;
    if ((getGetServerResponseMethod = ServerStreamingGrpc.getGetServerResponseMethod) == null) {
      synchronized (ServerStreamingGrpc.class) {
        if ((getGetServerResponseMethod = ServerStreamingGrpc.getGetServerResponseMethod) == null) {
          ServerStreamingGrpc.getGetServerResponseMethod = getGetServerResponseMethod =
              io.grpc.MethodDescriptor.<serverstreaming.ServerStreamingProto.Number, serverstreaming.ServerStreamingProto.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetServerResponse"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  serverstreaming.ServerStreamingProto.Number.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  serverstreaming.ServerStreamingProto.Message.getDefaultInstance()))
              .setSchemaDescriptor(new ServerStreamingMethodDescriptorSupplier("GetServerResponse"))
              .build();
        }
      }
    }
    return getGetServerResponseMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ServerStreamingStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerStreamingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerStreamingStub>() {
        @java.lang.Override
        public ServerStreamingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerStreamingStub(channel, callOptions);
        }
      };
    return ServerStreamingStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ServerStreamingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerStreamingBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerStreamingBlockingStub>() {
        @java.lang.Override
        public ServerStreamingBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerStreamingBlockingStub(channel, callOptions);
        }
      };
    return ServerStreamingBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ServerStreamingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerStreamingFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerStreamingFutureStub>() {
        @java.lang.Override
        public ServerStreamingFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerStreamingFutureStub(channel, callOptions);
        }
      };
    return ServerStreamingFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getServerResponse(serverstreaming.ServerStreamingProto.Number request,
        io.grpc.stub.StreamObserver<serverstreaming.ServerStreamingProto.Message> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetServerResponseMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ServerStreaming.
   */
  public static abstract class ServerStreamingImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ServerStreamingGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ServerStreaming.
   */
  public static final class ServerStreamingStub
      extends io.grpc.stub.AbstractAsyncStub<ServerStreamingStub> {
    private ServerStreamingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerStreamingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerStreamingStub(channel, callOptions);
    }

    /**
     */
    public void getServerResponse(serverstreaming.ServerStreamingProto.Number request,
        io.grpc.stub.StreamObserver<serverstreaming.ServerStreamingProto.Message> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetServerResponseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ServerStreaming.
   */
  public static final class ServerStreamingBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ServerStreamingBlockingStub> {
    private ServerStreamingBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerStreamingBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerStreamingBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<serverstreaming.ServerStreamingProto.Message> getServerResponse(
        serverstreaming.ServerStreamingProto.Number request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetServerResponseMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ServerStreaming.
   */
  public static final class ServerStreamingFutureStub
      extends io.grpc.stub.AbstractFutureStub<ServerStreamingFutureStub> {
    private ServerStreamingFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerStreamingFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerStreamingFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_SERVER_RESPONSE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SERVER_RESPONSE:
          serviceImpl.getServerResponse((serverstreaming.ServerStreamingProto.Number) request,
              (io.grpc.stub.StreamObserver<serverstreaming.ServerStreamingProto.Message>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetServerResponseMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              serverstreaming.ServerStreamingProto.Number,
              serverstreaming.ServerStreamingProto.Message>(
                service, METHODID_GET_SERVER_RESPONSE)))
        .build();
  }

  private static abstract class ServerStreamingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ServerStreamingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return serverstreaming.ServerStreamingProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ServerStreaming");
    }
  }

  private static final class ServerStreamingFileDescriptorSupplier
      extends ServerStreamingBaseDescriptorSupplier {
    ServerStreamingFileDescriptorSupplier() {}
  }

  private static final class ServerStreamingMethodDescriptorSupplier
      extends ServerStreamingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ServerStreamingMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ServerStreamingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ServerStreamingFileDescriptorSupplier())
              .addMethod(getGetServerResponseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
