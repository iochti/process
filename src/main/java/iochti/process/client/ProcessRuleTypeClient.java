package iochti.process.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import iochti.process.dto.ProcessRuleTypeDTO;
import iochti.process.grpc.ProcessRuleType;
import iochti.process.grpc.ProcessRuleTypeByIdRequest;
//import iochti.process.grpc.ProcessRuleType;
//import iochti.process.grpc.ProcessRuleTypeByIdRequest;
import iochti.process.grpc.ProcessRuleTypeGrpcAdapter;
//import iochti.process.grpc.ProcessRuleTypeServiceGrpc;

import iochti.process.grpc.ProcessRuleTypeServiceGrpc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProcessRuleTypeClient {
	private static final Logger logger = Logger
			.getLogger(ProcessRuleTypeClient.class.getName());
	
	@Autowired
	private ProcessRuleTypeGrpcAdapter grpcAdapter;

	private final ManagedChannel channel;
	private final ProcessRuleTypeServiceGrpc.ProcessRuleTypeServiceBlockingStub blockingStub;

	public ProcessRuleTypeClient() {
		this("localhost", 50052);
	}
	
	public ProcessRuleTypeClient(String host, int port) {
		channel = ManagedChannelBuilder.forAddress(host, port)
				.usePlaintext(true).build();
		blockingStub = ProcessRuleTypeServiceGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public void createProcessRuleType(ProcessRuleTypeDTO dto) {
		try {
			logger.info("Will try to create a process rule type "
					+ dto.getName() + " ...");
			ProcessRuleType.Builder builder = ProcessRuleType.newBuilder();
			if (dto.getId() != null) {
				builder.setId(dto.getId());
			}
			builder.setName(dto.getName());
			ObjectMapper objectMapper = new ObjectMapper();
			builder.setParameters(objectMapper.writeValueAsString(dto
					.getParameters()));
			ProcessRuleType request = builder.build();
			blockingStub.createProcessRuleType(request);
			logger.info("Data sent");
		} catch (RuntimeException | JsonProcessingException e) {
			logger.log(Level.WARNING, "RPC failed", e);
			return;
		}
	}
	
	public ProcessRuleTypeDTO getProcessRuleType(String id) throws JsonParseException, JsonMappingException, IOException {
		try {
			logger.info("Will try to get the process rule type "
					+ id + " ...");
			ProcessRuleTypeByIdRequest.Builder builder = ProcessRuleTypeByIdRequest.newBuilder();
			builder.setProcessGroupTypeId(id != null ? id : "");
			ProcessRuleTypeByIdRequest request = builder.build();
			logger.info("Data received");
			ProcessRuleType processRuleType = blockingStub.getProcessRuleType(request);
			ProcessRuleTypeDTO dto = grpcAdapter.buildDto(processRuleType);
			
			return dto;
		} catch (RuntimeException e) {
			logger.log(Level.WARNING, "RPC failed", e);
			throw e;
		}
	}
}
