package iochti.process.service.rule;

import iochti.process.document.ProcessDataDocument;
import iochti.process.dto.ProcessDataDTO;
import iochti.process.dto.ProcessRuleDTO;
import iochti.process.repository.ProcessDataRepository;
import iochti.process.service.IndicatorManager;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FieldValueAverage implements RuleInterface {
	
	private static final Logger logger = Logger
			.getLogger(FieldValueAverage.class.getName());
	
	@Autowired
	private ProcessDataRepository repository;
	
	@Autowired
	private IndicatorManager indicatorManager;

	@Override
	public void process(ProcessDataDTO data, ProcessRuleDTO processRule) {
		Properties ruleParameters = processRule.getParameters();
		String fieldName = ruleParameters.getProperty("field name");
		Double average = getFieldValueAverage(fieldName);
		indicatorManager.setIndicator(processRule.getName(), String.valueOf(average));
		
		logger.info("New indicator value : " + processRule.getName() + " : " + String.valueOf(average));
	}
	
	private Double getFieldValueAverage(String fieldName) throws NumberFormatException {
		Instant now = Instant.now();
		Duration duration = Duration.ofDays(7);
		Instant start = now.minus(duration);
		Date createdAt = Date.from(start);
		List<ProcessDataDocument> data = repository.findByCreatedAtGreaterThan(createdAt);
		ObjectMapper objectMapper = new ObjectMapper();
		DoubleStream doubleStream = data.stream().flatMapToDouble(x -> {
			Properties properties;
			try {
				properties = objectMapper.readValue(x.getPayload(), Properties.class);
			} catch (Exception e) {
				properties = new Properties();
			}
			String valueAsString = properties.getProperty(fieldName);
			Double value = Double.valueOf(valueAsString);
			
			return DoubleStream.of(value);
		});
		
		return doubleStream.sum() / data.size();
	}
}
