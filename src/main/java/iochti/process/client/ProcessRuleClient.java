package iochti.process.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import iochti.process.dto.ProcessRuleDTO;
import iochti.process.grpc.IdRequest;
import iochti.process.grpc.ProcessRule;
import iochti.process.grpc.ProcessRuleGrpcAdapter;
import iochti.process.grpc.ProcessRuleServiceGrpc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessRuleClient {
	private static final Logger logger = Logger
			.getLogger(ProcessRuleClient.class.getName());
	private final ManagedChannel channel;
	private final ProcessRuleServiceGrpc.ProcessRuleServiceBlockingStub blockingStub;

	private final static String HOST = "localhost";
	private final static Integer PORT = 50052;

	@Autowired
	private ProcessRuleGrpcAdapter grpcAdapter;

	public ProcessRuleClient() {
		this(HOST, PORT);
	}

	/** Construct client connecting to HelloWorld server at {@code host:port}. */
	public ProcessRuleClient(String host, int port) {
		channel = ManagedChannelBuilder.forAddress(host, port)
				.usePlaintext(true).build();
		blockingStub = ProcessRuleServiceGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public List<ProcessRuleDTO> findByThingGroupId(String id) {
		List<ProcessRuleDTO> result = new ArrayList<ProcessRuleDTO>();
		IdRequest.Builder builder = IdRequest.newBuilder();
		builder.setId(id);
		IdRequest request = builder.build();

		Iterator<ProcessRule> iterator = blockingStub
				.findByThingGroupId(request);
		iterator.forEachRemaining(grpcObject -> {
			try {
				result.add(grpcAdapter.buildDto(grpcObject));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		return result;
	}
}
