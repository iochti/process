/*
 * Copyright 2015, Google Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *
 *    * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package iochti.process.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import iochti.process.client.ProcessRuleClient;
import iochti.process.dto.ProcessDataDTO;
import iochti.process.grpc.DataProcessGrpc;
import iochti.process.grpc.DataProcessGrpc.DataProcess;
import iochti.process.grpc.ProcessData;
import iochti.process.grpc.ProcessDataGrpcAdapter;
import iochti.process.service.DataProcessManager;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.protobuf.Empty;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
@Service
public class DataProcessServer {
	
	private static final Logger logger = Logger
			.getLogger(DataProcessServer.class.getName());

	/* The port on which the server should run */
	private int port = 50051;
	private Server server;
	
	@Autowired
	private ProcessRuleClient processRuleClient;
	
	@Autowired
	private DataProcessManager manager;
	
	@Autowired
	private ProcessDataGrpcAdapter adapter;

	@PostConstruct
	private void PostConstruct() throws Exception {
		start();
	}

	private void start() throws Exception {
		server = ServerBuilder.forPort(port)
				.addService(DataProcessGrpc.bindService(new DataProcessImpl()))
				.build().start();
		logger.info("Server started, listening on " + port);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may have been reset by its
				// JVM shutdown hook.
				System.err
						.println("*** shutting down gRPC server since JVM is shutting down");
				DataProcessServer.this.stop();
				System.err.println("*** server shut down");
			}
		});
	}

	private void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	private class DataProcessImpl implements DataProcess {

		public void process(ProcessData request,
				StreamObserver<Empty> responseObserver) {

			logger.info("Data received : " + request.getId() + " - "
					+ request.getDataTypeId() + " - "
					+ request.getThingGroupId() + " - "
					+ request.getCreatedAt().toString() + " - "
					+ request.getPayload());
			
			try {
				ProcessDataDTO dto = adapter.buildDto(request);
				manager.process(dto);
				Empty empty = Empty.newBuilder().build();
				responseObserver.onNext(empty);
			} catch (JsonParseException e) {
				responseObserver.onError(e);
			} catch (JsonMappingException e) {
				responseObserver.onError(e);
			} catch (IOException e) {
				responseObserver.onError(e);
			}
			
			responseObserver.onCompleted();
		}
	}
}
