package iochti.process.grpc;

import iochti.process.dto.ProcessDataDTO;
import iochti.process.grpc.ProcessData.Builder;

import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.protobuf.Timestamp;

@Service
public class ProcessDataGrpcAdapter {
	/**
	 * Transform a data transfer object to a grpc object
	 * 
	 * @param dto
	 * @return
	 * @throws JsonProcessingException
	 */
	public ProcessData buildGrpc(ProcessDataDTO dto) throws JsonProcessingException {
		Builder builder = ProcessData.newBuilder();
		builder.setId(dto.getId() == null ? "" : dto.getId());
		builder.setDataTypeId(dto.getDataTypeId());
		builder.setThingGroupId(dto.getThingGroupId());
		builder.setCreatedAt(Timestamp.newBuilder().setSeconds(
					dto.getCreatedAt().getTime()));
		builder.setPayload(dto.getPayload());
		
		return builder.build();
	}
	
	/**
	 * Transform a grpc object to a data transfer object
	 * 
	 * @param grpc
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public ProcessDataDTO buildDto(ProcessData grpc) throws JsonParseException, JsonMappingException, IOException {
		ProcessDataDTO dto = new ProcessDataDTO();
		dto.setId("".equals(grpc.getId()) ? null : grpc.getId());
		dto.setDataTypeId(grpc.getDataTypeId());
		dto.setThingGroupId(grpc.getThingGroupId());
		Date createdAt = new Date(grpc.getCreatedAt().getSeconds());
		dto.setCreatedAt(createdAt);
		dto.setPayload(grpc.getPayload());
		
		return dto;
	}
}
