package iochti.process.service.rule;

import iochti.process.dto.ProcessDataDTO;
import iochti.process.dto.ProcessRuleDTO;

public interface RuleInterface {
	void process(ProcessDataDTO data, ProcessRuleDTO processRule);
}
