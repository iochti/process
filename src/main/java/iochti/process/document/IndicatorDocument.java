package iochti.process.document;

import org.springframework.data.annotation.Id;

public class IndicatorDocument {

	/**
	 * Id
	 */
	@Id
	private String id;
	
	/**
	 * Value
	 */
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
