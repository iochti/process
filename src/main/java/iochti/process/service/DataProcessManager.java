package iochti.process.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iochti.process.client.ProcessRuleClient;
import iochti.process.client.ProcessRuleTypeClient;
import iochti.process.document.ProcessDataDocumentAdapter;
import iochti.process.dto.ProcessDataDTO;
import iochti.process.dto.ProcessRuleDTO;
import iochti.process.dto.ProcessRuleTypeDTO;
import iochti.process.repository.ProcessDataRepository;
import iochti.process.service.rule.FieldValueAverage;
import iochti.process.service.rule.RuleInterface;

@Service
public class DataProcessManager {

	private static final Logger logger = Logger
			.getLogger(DataProcessManager.class.getName());

	@Autowired
	private ProcessRuleClient processRuleClient;

	@Autowired
	private ProcessRuleTypeClient processRuleTypeClient;

	@Autowired
	private FieldValueAverage fieldValueAverage;
	
	@Autowired
	private ProcessDataRepository repository;
	
	@Autowired
	private ProcessDataDocumentAdapter adapter;

	/**
	 * Process the received data
	 * 
	 * @param dto
	 */
	public void process(ProcessDataDTO dto) {
		repository.save(adapter.buildDocument(dto));
		
		List<ProcessRuleDTO> processRules = processRuleClient
				.findByThingGroupId(dto.getThingGroupId());

		processRules.forEach(x -> {
			applyProcessRule(x, dto);
		});
	}

	/**
	 * Apply a rule
	 * 
	 * @param ruleDTO
	 */
	private void applyProcessRule(ProcessRuleDTO ruleDTO, ProcessDataDTO dataDTO) {
		ProcessRuleTypeDTO ruleType = null;

		/*
		try {
			ruleType = processRuleTypeClient.getProcessRuleType(ruleDTO
					.getRuleTypeId());
		} catch (IOException e) {
			logger.warning("Failed to apply rule " + ruleDTO.getId() + "("
					+ ruleDTO.getName() + ") for data " + dataDTO.getId());
		}
		*/

		RuleInterface rule = null;

		// TODO : Get a not hard coded bean for rule type
		/*
		if ("Field value average".equals(ruleType.getName())) {
			rule = fieldValueAverage;
		}
		*/
		rule = fieldValueAverage;

		if (rule != null) {
			rule.process(dataDTO, ruleDTO);
		} else {
			logger.warning("The rule type " + ruleType.getName() + "("
					+ ruleType.getId() + ") is not implemented.");
		}
	}
}
