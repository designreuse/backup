
package com.nvidia.cosmos.cloud.dtos.job;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "owner_name", "serial_number","label"})
public class Labels {
	@JsonProperty("owner_name")
	private String ownerName;
	@JsonProperty("serial_number")
	private String serialNumber;
	@JsonProperty("label")
	private String label;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	* 
	* @return
	* The ownerName
	*/
	@JsonProperty("owner_name")
	public String getOwnerName() {
	return ownerName;
	}

	/**
	* 
	* @param ownerName
	* The owner_name
	*/
	@JsonProperty("owner_name")
	public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
	}

	/**
	* 
	* @return
	* The serialNumber
	*/
	@JsonProperty("serial_number")
	public String getSerialNumber() {
	return serialNumber;
	}
	
	

	/**
	 * @return the label
	 */
	@JsonProperty("label")
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	@JsonProperty("label")
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	* 
	* @param serialNumber
	* The serial_number
	*/
	@JsonProperty("serial_number")
	public void setSerialNumber(String serialNumber) {
	this.serialNumber = serialNumber;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}
	
	/*@JsonProperty("value")
    private String value;

	@JsonProperty("key")
    private String key;

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [value = "+value+", key = "+key+"]";
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }*/

}
