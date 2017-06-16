package iochti.process.service;

import java.util.ArrayList;
import java.util.List;

import iochti.process.document.IndicatorDocument;
import iochti.process.document.IndicatorDocumentAdapter;
import iochti.process.dto.IndicatorDTO;
import iochti.process.repository.IndicatorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndicatorManager {
	
	@Autowired
	IndicatorRepository repository;
	
	@Autowired
	IndicatorDocumentAdapter adapter;
	
	/**
	 * Set an indicator
	 * 
	 * @param key
	 * @param value
	 */
	public void setIndicator(String key, String value) {
		IndicatorDTO dto = new IndicatorDTO();
		dto.setId(key);
		dto.setValue(value);
		repository.save(adapter.buildDocument(dto));
	}
	
	/**
	 * Get an indicator
	 * 
	 * @param id
	 * @return
	 */
	public IndicatorDTO getIndicator(String id) {
		IndicatorDocument document = repository.findOne(id);
		IndicatorDTO dto = adapter.buildDTO(document);
		
		return dto;
	}
	
	/**
	 * Get all indicators
	 * 
	 * @return
	 */
	public List<IndicatorDTO> getAllIndicators() {
		List<IndicatorDocument> indicators = repository.findAll();
		List<IndicatorDTO> result = new ArrayList<IndicatorDTO>();
		
		indicators.forEach(x -> {
			result.add(adapter.buildDTO(x));
		});
		
		return result;
	}
}
