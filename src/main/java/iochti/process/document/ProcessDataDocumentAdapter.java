package iochti.process.document;

import org.springframework.stereotype.Service;

import iochti.process.dto.ProcessDataDTO;

@Service
public class ProcessDataDocumentAdapter {
	public ProcessDataDTO buildDTO(ProcessDataDocument document) {
		ProcessDataDTO result = new ProcessDataDTO();
		result.setId(document.getId());
		result.setDataTypeId(document.getDataTypeId());
		result.setThingGroupId(document.getThingGroupId());
		result.setCreatedAt(document.getCreatedAt());
		result.setPayload(document.getPayload());
		
		return result;
	}
	
	public ProcessDataDocument buildDocument(ProcessDataDTO dto) {
		ProcessDataDocument result = new ProcessDataDocument();
		result.setId(dto.getId());
		result.setDataTypeId(dto.getDataTypeId());
		result.setThingGroupId(dto.getThingGroupId());
		result.setCreatedAt(dto.getCreatedAt());
		result.setPayload(dto.getPayload());
		
		return result;
	}
}
