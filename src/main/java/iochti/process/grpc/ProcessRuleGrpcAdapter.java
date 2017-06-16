package iochti.process.grpc;

import iochti.process.dto.ProcessRuleDTO;
import iochti.process.grpc.ProcessRule.Builder;

import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProcessRuleGrpcAdapter {
	
	/**
	 * Transform a data transfer object to a grpc object
	 * 
	 * @param dto
	 * @return
	 * @throws JsonProcessingException
	 */
	public ProcessRule buildGrpc(ProcessRuleDTO dto) throws JsonProcessingException {
		Builder builder = ProcessRule.newBuilder();
		builder.setId(dto.getId() == null ? "" : dto.getId());
		builder.setRuleTypeId(dto.getRuleTypeId());
		builder.setThingGroupId(dto.getThingGroupId());
		builder.setName(dto.getName());
		ObjectMapper mapper = new ObjectMapper();
		String serializedParameters = mapper.writeValueAsString(dto.getParameters());
		builder.setParameters(serializedParameters);
		
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
	public ProcessRuleDTO buildDto(ProcessRule grpc) throws JsonParseException, JsonMappingException, IOException {
		ProcessRuleDTO dto = new ProcessRuleDTO();
		dto.setId("".equals(grpc.getId()) ? null : grpc.getId());
		dto.setRuleTypeId(grpc.getRuleTypeId());
		dto.setThingGroupId(grpc.getThingGroupId());
		dto.setName(grpc.getName());
		ObjectMapper mapper = new ObjectMapper();
		Properties parameters = mapper.readValue(grpc.getParameters(), Properties.class);
		dto.setParameters(parameters);
		return dto;
	}
}
