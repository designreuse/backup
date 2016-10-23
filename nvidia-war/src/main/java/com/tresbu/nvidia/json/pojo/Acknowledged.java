
package com.tresbu.nvidia.json.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "Active", "Status", "Silenced", "CurrentStatus", "Subject", "Children" })
public class Acknowledged {

	@JsonProperty("Active")
	private Boolean Active;
	@JsonProperty("Status")
	private String Status;
	@JsonProperty("Silenced")
	private Boolean Silenced;
	@JsonProperty("CurrentStatus")
	private String CurrentStatus;
	@JsonProperty("Subject")
	private String Subject;
	@JsonProperty("Children")
	private List<Child> Children = new ArrayList<Child>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The Active
	 */
	@JsonProperty("Active")
	public Boolean getActive() {
		return Active;
	}

	/**
	 * 
	 * @param Active
	 *            The Active
	 */
	@JsonProperty("Active")
	public void setActive(Boolean Active) {
		this.Active = Active;
	}

	/**
	 * 
	 * @return The Status
	 */
	@JsonProperty("Status")
	public String getStatus() {
		return Status;
	}

	/**
	 * 
	 * @param Status
	 *            The Status
	 */
	@JsonProperty("Status")
	public void setStatus(String Status) {
		this.Status = Status;
	}

	/**
	 * 
	 * @return The Silenced
	 */
	@JsonProperty("Silenced")
	public Boolean getSilenced() {
		return Silenced;
	}

	/**
	 * 
	 * @param Silenced
	 *            The Silenced
	 */
	@JsonProperty("Silenced")
	public void setSilenced(Boolean Silenced) {
		this.Silenced = Silenced;
	}

	/**
	 * 
	 * @return The CurrentStatus
	 */
	@JsonProperty("CurrentStatus")
	public String getCurrentStatus() {
		return CurrentStatus;
	}

	/**
	 * 
	 * @param CurrentStatus
	 *            The CurrentStatus
	 */
	@JsonProperty("CurrentStatus")
	public void setCurrentStatus(String CurrentStatus) {
		this.CurrentStatus = CurrentStatus;
	}

	/**
	 * 
	 * @return The Subject
	 */
	@JsonProperty("Subject")
	public String getSubject() {
		return Subject;
	}

	/**
	 * 
	 * @param Subject
	 *            The Subject
	 */
	@JsonProperty("Subject")
	public void setSubject(String Subject) {
		this.Subject = Subject;
	}

	/**
	 * 
	 * @return The Children
	 */
	@JsonProperty("Children")
	public List<Child> getChildren() {
		return Children;
	}

	/**
	 * 
	 * @param Children
	 *            The Children
	 */
	@JsonProperty("Children")
	public void setChildren(List<Child> Children) {
		this.Children = Children;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
