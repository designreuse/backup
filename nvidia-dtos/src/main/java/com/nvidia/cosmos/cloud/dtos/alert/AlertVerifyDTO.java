package com.nvidia.cosmos.cloud.dtos.alert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nvidia.cosmos.cloud.dtos.BaseDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "key_id", "action_id" })
public class AlertVerifyDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("key_id")
	private String keyId;
	@JsonProperty("action_id")
	private String actionId;

	/**
	 * @return the keyId
	 */
	@JsonProperty("key_id")
	public String getKeyId() {
		return keyId;
	}

	/**
	 * @param keyId
	 *            the keyId to set
	 */
	@JsonProperty("key_id")
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	/**
	 * @return the actionId
	 */
	@JsonProperty("action_id")
	public String getActionId() {
		return actionId;
	}

	/**
	 * @param actionId
	 *            the actionId to set
	 */
	@JsonProperty("action_id")
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public AlertVerifyDTO() {

	}

	/**
	 * @param keyId
	 * @param actionId
	 */
	public AlertVerifyDTO(String keyId, String actionId) {
		super();
		this.keyId = keyId;
		this.actionId = actionId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AlertVerify [keyId=" + keyId + ", actionId=" + actionId + "]";
	}

}
