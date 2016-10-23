
package com.nvidia.cosmos.cloud.dtos;

import java.util.HashMap;
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
@JsonPropertyOrder({ "User", "Message", "Time", "Type" })
public class ActionDTO {

	@JsonProperty("User")
	private String User;
	@JsonProperty("Message")
	private String Message;
	@JsonProperty("Time")
	private String Time;
	@JsonProperty("Type")
	private String Type;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The User
	 */
	@JsonProperty("User")
	public String getUser() {
		return User;
	}

	/**
	 * 
	 * @param User
	 *            The User
	 */
	@JsonProperty("User")
	public void setUser(String User) {
		this.User = User;
	}

	/**
	 * 
	 * @return The Message
	 */
	@JsonProperty("Message")
	public String getMessage() {
		return Message;
	}

	/**
	 * 
	 * @param Message
	 *            The Message
	 */
	@JsonProperty("Message")
	public void setMessage(String Message) {
		this.Message = Message;
	}

	/**
	 * 
	 * @return The Time
	 */
	@JsonProperty("Time")
	public String getTime() {
		return Time;
	}

	/**
	 * 
	 * @param Time
	 *            The Time
	 */
	@JsonProperty("Time")
	public void setTime(String Time) {
		this.Time = Time;
	}

	/**
	 * 
	 * @return The Type
	 */
	@JsonProperty("Type")
	public String getType() {
		return Type;
	}

	/**
	 * 
	 * @param Type
	 *            The Type
	 */
	@JsonProperty("Type")
	public void setType(String Type) {
		this.Type = Type;
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
