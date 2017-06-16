package iochti.process.document;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class ProcessDataDocument {
	
	/**
	 * Id
	 */
	@Id
	private String id;
	
	/**
	 * Data type id
	 */
	private String dataTypeId;
	
	/**
	 * Thing group id
	 */
	private String thingGroupId;
	
	/**
	 * Created at
	 */
	private Date createdAt;
	
	/**
	 * Payload
	 */
	private String payload;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataTypeId() {
		return dataTypeId;
	}

	public void setDataTypeId(String dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public String getThingGroupId() {
		return thingGroupId;
	}

	public void setThingGroupId(String thingGroupId) {
		this.thingGroupId = thingGroupId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}
}
