package iochti.process.document;

import iochti.process.dto.IndicatorDTO;

import org.springframework.stereotype.Service;

@Service
public class IndicatorDocumentAdapter {
	
	public IndicatorDTO buildDTO(IndicatorDocument document) {
		IndicatorDTO result = new IndicatorDTO();
		result.setId(document.getId());
		result.setValue(document.getValue());
		
		return result;
	}
	
	public IndicatorDocument buildDocument(IndicatorDTO dto) {
		IndicatorDocument result = new IndicatorDocument();
		result.setId(dto.getId());
		result.setValue(dto.getValue());
		
		return result;
	}
}
