package clientstreaming;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.0)",
    comments = "Source: clientstreaming.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ClientStreamingGrpc {

  private ClientStreamingGrpc() {}

  public static final java.lang.String SERVICE_NAME = "clientstreaming.ClientStreaming";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<clientstreaming.ClientStreamingProto.Message,
      clientstreaming.ClientStreamingProto.Number> getGetServerResponseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetServerResponse",
      requestType = clientstreaming.ClientStreamingProto.Message.class,
      responseType = clientstreaming.ClientStreamingProto.Number.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<clientstreaming.ClientStreamingProto.Message,
      clientstreaming.ClientStreamingProto.Number> getGetServerResponseMethod() {
    io.grpc.MethodDescriptor<clientstreaming.ClientStreamingProto.Message, clientstreaming.ClientStreamingProto.Number> getGetServerResponseMethod;
    if ((getGetServerResponseMethod = ClientStreamingGrpc.getGetServerResponseMethod) == null) {
      synchronized (ClientStreamingGrpc.class) {
        if ((getGetServerResponseMethod = ClientStreamingGrpc.getGetServerResponseMethod) == null) {
          ClientStreamingGrpc.getGetServerResponseMethod = getGetServerResponseMethod =
              io.grpc.MethodDescriptor.<clientstreaming.ClientStreamingProto.Message, clientstreaming.ClientStreamingProto.Number>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetServerResponse"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  clientstreaming.ClientStreamingProto.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  clientstreaming.ClientStreamingProto.Number.getDefaultInstance()))
              .setSchemaDescriptor(new ClientStreamingMethodDescriptorSupplier("GetServerResponse"))
              .build();
        }
      }
    }
    return getGetServerResponseMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClientStreamingStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientStreamingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientStreamingStub>() {
        @java.lang.Override
        public ClientStreamingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientStreamingStub(channel, callOptions);
        }
      };
    return ClientStreamingStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClientStreamingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientStreamingBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientStreamingBlockingStub>() {
        @java.lang.Override
        public ClientStreamingBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientStreamingBlockingStub(channel, callOptions);
        }
      };
    return ClientStreamingBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClientStreamingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientStreamingFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientStreamingFutureStub>() {
        @java.lang.Override
        public ClientStreamingFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientStreamingFutureStub(channel, callOptions);
        }
      };
    return ClientStreamingFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<clientstreaming.ClientStreamingProto.Message> getServerResponse(
        io.grpc.stub.StreamObserver<clientstreaming.ClientStreamingProto.Number> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getGetServerResponseMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ClientStreaming.
   */
  public static abstract class ClientStreamingImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ClientStreamingGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ClientStreaming.
   */
  public static final class ClientStreamingStub
      extends io.grpc.stub.AbstractAsyncStub<ClientStreamingStub> {
    private ClientStreamingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientStreamingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientStreamingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<clientstreaming.ClientStreamingProto.Message> getServerResponse(
        io.grpc.stub.StreamObserver<clientstreaming.ClientStreamingProto.Number> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getGetServerResponseMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ClientStreaming.
   */
  public static final class ClientStreamingBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ClientStreamingBlockingStub> {
    private ClientStreamingBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientStreamingBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientStreamingBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ClientStreaming.
   */
  public static final class ClientStreamingFutureStub
      extends io.grpc.stub.AbstractFutureStub<ClientStreamingFutureStub> {
    private ClientStreamingFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientStreamingFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientStreamingFutureStub(channel, callOptions);
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
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SERVER_RESPONSE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getServerResponse(
              (io.grpc.stub.StreamObserver<clientstreaming.ClientStreamingProto.Number>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetServerResponseMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              clientstreaming.ClientStreamingProto.Message,
              clientstreaming.ClientStreamingProto.Number>(
                service, METHODID_GET_SERVER_RESPONSE)))
        .build();
  }

  private static abstract class ClientStreamingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClientStreamingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return clientstreaming.ClientStreamingProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClientStreaming");
    }
  }

  private static final class ClientStreamingFileDescriptorSupplier
      extends ClientStreamingBaseDescriptorSupplier {
    ClientStreamingFileDescriptorSupplier() {}
  }

  private static final class ClientStreamingMethodDescriptorSupplier
      extends ClientStreamingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ClientStreamingMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ClientStreamingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClientStreamingFileDescriptorSupplier())
              .addMethod(getGetServerResponseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
