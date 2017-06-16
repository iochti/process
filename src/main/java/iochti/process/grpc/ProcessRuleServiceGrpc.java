package iochti.process.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class ProcessRuleServiceGrpc {

  private ProcessRuleServiceGrpc() {}

  public static final String SERVICE_NAME = "proto.ProcessRuleService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<IdRequest,
      ProcessRule> METHOD_FIND_BY_THING_GROUP_ID =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "proto.ProcessRuleService", "FindByThingGroupId"),
          io.grpc.protobuf.ProtoUtils.marshaller(IdRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(ProcessRule.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<IdRequest,
      ProcessRule> METHOD_GET_PROCESS_RULE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "proto.ProcessRuleService", "GetProcessRule"),
          io.grpc.protobuf.ProtoUtils.marshaller(IdRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(ProcessRule.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<ProcessRule,
      ProcessRule> METHOD_CREATE_PROCESS_RULE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "proto.ProcessRuleService", "CreateProcessRule"),
          io.grpc.protobuf.ProtoUtils.marshaller(ProcessRule.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(ProcessRule.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<ProcessRule,
      ProcessRule> METHOD_UPDATE_PROCESS_RULE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "proto.ProcessRuleService", "UpdateProcessRule"),
          io.grpc.protobuf.ProtoUtils.marshaller(ProcessRule.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(ProcessRule.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<IdRequest,
      com.google.protobuf.Empty> METHOD_DELETE_PROCESS_RULE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "proto.ProcessRuleService", "DeleteProcessRule"),
          io.grpc.protobuf.ProtoUtils.marshaller(IdRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()));

  public static ProcessRuleServiceStub newStub(io.grpc.Channel channel) {
    return new ProcessRuleServiceStub(channel);
  }

  public static ProcessRuleServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ProcessRuleServiceBlockingStub(channel);
  }

  public static ProcessRuleServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ProcessRuleServiceFutureStub(channel);
  }

  public static interface ProcessRuleService {

    public void findByThingGroupId(IdRequest request,
        io.grpc.stub.StreamObserver<ProcessRule> responseObserver);

    public void getProcessRule(IdRequest request,
        io.grpc.stub.StreamObserver<ProcessRule> responseObserver);

    public void createProcessRule(ProcessRule request,
        io.grpc.stub.StreamObserver<ProcessRule> responseObserver);

    public void updateProcessRule(ProcessRule request,
        io.grpc.stub.StreamObserver<ProcessRule> responseObserver);

    public void deleteProcessRule(IdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver);
  }

  public static interface ProcessRuleServiceBlockingClient {

    public java.util.Iterator<ProcessRule> findByThingGroupId(
        IdRequest request);

    public ProcessRule getProcessRule(IdRequest request);

    public ProcessRule createProcessRule(ProcessRule request);

    public ProcessRule updateProcessRule(ProcessRule request);

    public com.google.protobuf.Empty deleteProcessRule(IdRequest request);
  }

  public static interface ProcessRuleServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<ProcessRule> getProcessRule(
        IdRequest request);

    public com.google.common.util.concurrent.ListenableFuture<ProcessRule> createProcessRule(
        ProcessRule request);

    public com.google.common.util.concurrent.ListenableFuture<ProcessRule> updateProcessRule(
        ProcessRule request);

    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteProcessRule(
        IdRequest request);
  }

  public static class ProcessRuleServiceStub extends io.grpc.stub.AbstractStub<ProcessRuleServiceStub>
      implements ProcessRuleService {
    private ProcessRuleServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProcessRuleServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProcessRuleServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProcessRuleServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void findByThingGroupId(IdRequest request,
        io.grpc.stub.StreamObserver<ProcessRule> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_FIND_BY_THING_GROUP_ID, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void getProcessRule(IdRequest request,
        io.grpc.stub.StreamObserver<ProcessRule> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_PROCESS_RULE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void createProcessRule(ProcessRule request,
        io.grpc.stub.StreamObserver<ProcessRule> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_PROCESS_RULE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void updateProcessRule(ProcessRule request,
        io.grpc.stub.StreamObserver<ProcessRule> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PROCESS_RULE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void deleteProcessRule(IdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_PROCESS_RULE, getCallOptions()), request, responseObserver);
    }
  }

  public static class ProcessRuleServiceBlockingStub extends io.grpc.stub.AbstractStub<ProcessRuleServiceBlockingStub>
      implements ProcessRuleServiceBlockingClient {
    private ProcessRuleServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProcessRuleServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProcessRuleServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProcessRuleServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public java.util.Iterator<ProcessRule> findByThingGroupId(
        IdRequest request) {
      return blockingServerStreamingCall(
          getChannel().newCall(METHOD_FIND_BY_THING_GROUP_ID, getCallOptions()), request);
    }

    @java.lang.Override
    public ProcessRule getProcessRule(IdRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_GET_PROCESS_RULE, getCallOptions()), request);
    }

    @java.lang.Override
    public ProcessRule createProcessRule(ProcessRule request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_CREATE_PROCESS_RULE, getCallOptions()), request);
    }

    @java.lang.Override
    public ProcessRule updateProcessRule(ProcessRule request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PROCESS_RULE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.protobuf.Empty deleteProcessRule(IdRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_DELETE_PROCESS_RULE, getCallOptions()), request);
    }
  }

  public static class ProcessRuleServiceFutureStub extends io.grpc.stub.AbstractStub<ProcessRuleServiceFutureStub>
      implements ProcessRuleServiceFutureClient {
    private ProcessRuleServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProcessRuleServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProcessRuleServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProcessRuleServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<ProcessRule> getProcessRule(
        IdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_PROCESS_RULE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<ProcessRule> createProcessRule(
        ProcessRule request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_PROCESS_RULE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<ProcessRule> updateProcessRule(
        ProcessRule request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PROCESS_RULE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteProcessRule(
        IdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_PROCESS_RULE, getCallOptions()), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final ProcessRuleService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
      .addMethod(
        METHOD_FIND_BY_THING_GROUP_ID,
        asyncServerStreamingCall(
          new io.grpc.stub.ServerCalls.ServerStreamingMethod<
              IdRequest,
              ProcessRule>() {
            @java.lang.Override
            public void invoke(
                IdRequest request,
                io.grpc.stub.StreamObserver<ProcessRule> responseObserver) {
              serviceImpl.findByThingGroupId(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_GET_PROCESS_RULE,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              IdRequest,
              ProcessRule>() {
            @java.lang.Override
            public void invoke(
                IdRequest request,
                io.grpc.stub.StreamObserver<ProcessRule> responseObserver) {
              serviceImpl.getProcessRule(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_CREATE_PROCESS_RULE,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              ProcessRule,
              ProcessRule>() {
            @java.lang.Override
            public void invoke(
                ProcessRule request,
                io.grpc.stub.StreamObserver<ProcessRule> responseObserver) {
              serviceImpl.createProcessRule(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_UPDATE_PROCESS_RULE,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              ProcessRule,
              ProcessRule>() {
            @java.lang.Override
            public void invoke(
                ProcessRule request,
                io.grpc.stub.StreamObserver<ProcessRule> responseObserver) {
              serviceImpl.updateProcessRule(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_DELETE_PROCESS_RULE,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              IdRequest,
              com.google.protobuf.Empty>() {
            @java.lang.Override
            public void invoke(
                IdRequest request,
                io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
              serviceImpl.deleteProcessRule(request, responseObserver);
            }
          })).build();
  }
}
