package iochti.process.dto;

import java.util.Properties;

public class ProcessRuleDTO {
	
	/**
	 * Id
	 */
	private String id;
	
	/**
	 * Name
	 */
	private String name;
	
	/**
	 * Rule type id
	 */
	private String ruleTypeId;
	
	/**
	 * Thing group id
	 */
	private String thingGroupId;
	
	/**
	 * Parameters
	 */
	private Properties parameters;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRuleTypeId() {
		return ruleTypeId;
	}

	public void setRuleTypeId(String ruleTypeId) {
		this.ruleTypeId = ruleTypeId;
	}

	public String getThingGroupId() {
		return thingGroupId;
	}

	public void setThingGroupId(String thingGroupId) {
		this.thingGroupId = thingGroupId;
	}

	public Properties getParameters() {
		return parameters;
	}

	public void setParameters(Properties parameters) {
		this.parameters = parameters;
	}
}
