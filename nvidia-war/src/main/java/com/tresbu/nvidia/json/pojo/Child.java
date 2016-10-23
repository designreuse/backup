
package com.tresbu.nvidia.json.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Active", "Status", "Silenced", "CurrentStatus", "Alert", "AlertKey", "Ago", "State" })
public class Child {

	@JsonIgnore
	private Date alertDate;
	@JsonIgnore
	private String serialId;
	@JsonIgnore
	private String clusterId;
	@JsonIgnore
	private String alertName;
	@JsonProperty("Active")
	private boolean Active;
	@JsonProperty("Status")
	private String Status;
	@JsonProperty("Silenced")
	private boolean Silenced;
	@JsonProperty("CurrentStatus")
	private String CurrentStatus;
	@JsonProperty("Alert")
	private String Alert;
	@JsonProperty("AlertKey")
	private String AlertKey;
	@JsonProperty("Ago")
	private String Ago;
	@JsonProperty("State")
	private State State;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	@JsonIgnore
	private String alertStatus;
	
	@JsonIgnore
	public String getAlertStatus() {
		return alertStatus;
	}
	@JsonIgnore
	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}

	@JsonIgnore
	public Date getAlertDate() {
		return alertDate;
	}

	@JsonIgnore
	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}

	/**
	 * 
	 * @return The Active
	 */
	@JsonProperty("Active")
	public boolean isActive() {
		return Active;
	}

	@JsonIgnore
	public String getSerialId() {
		return serialId;
	}

	@JsonIgnore
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	@JsonIgnore
	public String getAlertName() {
		return alertName;
	}

	@JsonIgnore
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	@JsonIgnore
	public String getClusterId() {
		return clusterId;
	}

	@JsonIgnore
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	/**
	 * 
	 * @param Active
	 *            The Active
	 */
	@JsonProperty("Active")
	public void setActive(boolean Active) {
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
	public boolean isSilenced() {
		return Silenced;
	}

	/**
	 * 
	 * @param Silenced
	 *            The Silenced
	 */
	@JsonProperty("Silenced")
	public void setSilenced(boolean Silenced) {
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
	 * @return The Alert
	 */
	@JsonProperty("Alert")
	public String getAlert() {
		return Alert;
	}

	/**
	 * 
	 * @param Alert
	 *            The Alert
	 */
	@JsonProperty("Alert")
	public void setAlert(String Alert) {
		this.Alert = Alert;
	}

	/**
	 * 
	 * @return The AlertKey
	 */
	@JsonProperty("AlertKey")
	public String getAlertKey() {
		return AlertKey;
	}

	/**
	 * 
	 * @param AlertKey
	 *            The AlertKey
	 */
	@JsonProperty("AlertKey")
	public void setAlertKey(String AlertKey) {
		this.AlertKey = AlertKey;
	}

	/**
	 * 
	 * @return The Ago
	 */
	@JsonProperty("Ago")
	public String getAgo() {
		return Ago;
	}

	/**
	 * 
	 * @param Ago
	 *            The Ago
	 */
	@JsonProperty("Ago")
	public void setAgo(String Ago) {
		this.Ago = Ago;
	}

	/**
	 * 
	 * @return The State
	 */
	@JsonProperty("State")
	public State getState() {
		return State;
	}

	/**
	 * 
	 * @param State
	 *            The State
	 */
	@JsonProperty("State")
	public void setState(State State) {
		this.State = State;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "Child [alertDate=" + alertDate + ", serialId=" + serialId + ", clusterId=" + clusterId + ", alertName=" + alertName + ", Active="
				+ Active + ", Status=" + Status + ", Silenced=" + Silenced + ", CurrentStatus=" + CurrentStatus + ", Alert=" + Alert + ", AlertKey="
				+ AlertKey + ", Ago=" + Ago + ", State=" + State + ", additionalProperties=" + additionalProperties + "]";
	}

}
