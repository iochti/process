package iochti.process.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import iochti.process.dto.ProcessDataDTO;
import iochti.process.grpc.DataProcessGrpc;
import iochti.process.grpc.ProcessData;
import iochti.process.grpc.ProcessData.Builder;
import iochti.process.server.DataProcessServer;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;

/**
 * A simple client that requests a greeting from the {@link DataProcessServer}.
 */
public class DataProcessClient {
	private static final Logger logger = Logger
			.getLogger(DataProcessClient.class.getName());

	private final ManagedChannel channel;
	private final DataProcessGrpc.DataProcessBlockingStub blockingStub;

	/** Construct client connecting to HelloWorld server at {@code host:port}. */
	public DataProcessClient(String host, int port) {
		channel = ManagedChannelBuilder.forAddress(host, port)
				.usePlaintext(true).build();
		blockingStub = DataProcessGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	// Send data to the server
	public void sendData(ProcessDataDTO dto) {
		try {
			logger.info("Will try to send data " + dto.getId() + " ...");
			Builder builder = ProcessData.newBuilder();
			if (dto.getId() != null) {
				builder.setId(dto.getId());
			}
			builder.setDataTypeId(dto.getDataTypeId());
			builder.setThingGroupId(dto.getThingGroupId());
			builder.setCreatedAt(Timestamp.newBuilder().setSeconds(
					dto.getCreatedAt().getTime()));
			builder.setPayload(dto.getPayload());
			ProcessData request = builder.build();
			blockingStub.process(request);
			logger.info("Data sent");
		} catch (RuntimeException e) {
			logger.log(Level.WARNING, "RPC failed", e);
			return;
		}
	}

	/**
	 * Greet server. If provided, the first element of {@code args} is the name
	 * to use in the greeting.
	 */
	public static void main(String[] args) throws Exception {
		DataProcessClient client = new DataProcessClient("localhost", 50051);
		try {
			ProcessDataDTO dto = new ProcessDataDTO();
			dto.setDataTypeId("test");
			dto.setThingGroupId("thermometer");
			dto.setCreatedAt(new Date());
			
			ObjectMapper objectMapper = new ObjectMapper();
			Properties payload = new Properties();
			payload.setProperty("temperature", "27");
			dto.setPayload(objectMapper.writeValueAsString(payload));
			client.sendData(dto);
		} finally {
			client.shutdown();
		}
	}
}
