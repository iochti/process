package iochti.process.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class DataProcessGrpc {

  private DataProcessGrpc() {}

  public static final String SERVICE_NAME = "proto.DataProcess";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<ProcessData,
      com.google.protobuf.Empty> METHOD_PROCESS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "proto.DataProcess", "Process"),
          io.grpc.protobuf.ProtoUtils.marshaller(ProcessData.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()));

  public static DataProcessStub newStub(io.grpc.Channel channel) {
    return new DataProcessStub(channel);
  }

  public static DataProcessBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DataProcessBlockingStub(channel);
  }

  public static DataProcessFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DataProcessFutureStub(channel);
  }

  public static interface DataProcess {

    public void process(ProcessData request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver);
  }

  public static interface DataProcessBlockingClient {

    public com.google.protobuf.Empty process(ProcessData request);
  }

  public static interface DataProcessFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> process(
        ProcessData request);
  }

  public static class DataProcessStub extends io.grpc.stub.AbstractStub<DataProcessStub>
      implements DataProcess {
    private DataProcessStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataProcessStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataProcessStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataProcessStub(channel, callOptions);
    }

    @java.lang.Override
    public void process(ProcessData request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_PROCESS, getCallOptions()), request, responseObserver);
    }
  }

  public static class DataProcessBlockingStub extends io.grpc.stub.AbstractStub<DataProcessBlockingStub>
      implements DataProcessBlockingClient {
    private DataProcessBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataProcessBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataProcessBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataProcessBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.protobuf.Empty process(ProcessData request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_PROCESS, getCallOptions()), request);
    }
  }

  public static class DataProcessFutureStub extends io.grpc.stub.AbstractStub<DataProcessFutureStub>
      implements DataProcessFutureClient {
    private DataProcessFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataProcessFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataProcessFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataProcessFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> process(
        ProcessData request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_PROCESS, getCallOptions()), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final DataProcess serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
      .addMethod(
        METHOD_PROCESS,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              ProcessData,
              com.google.protobuf.Empty>() {
            @java.lang.Override
            public void invoke(
                ProcessData request,
                io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
              serviceImpl.process(request, responseObserver);
            }
          })).build();
  }
}
