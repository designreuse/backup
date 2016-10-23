
package com.tresbu.nvidia.json.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Computations", "Value", "Expr", "History", "Touched", "Alert", "Tags", "Group", "Subject", "Body", "NeedAck", "Acknowledge",
		"Open", "Forgotten", "Unevaluated", "LastLogTime" })
public class State {

	@JsonProperty("Computations")
	private Object Computations;
	@JsonProperty("Value")
	private long Value;
	@JsonProperty("Expr")
	private String Expr;
	@JsonProperty("History")
	private List<History> History = new ArrayList<History>();
	@JsonProperty("Touched")
	private String Touched;
	@JsonProperty("Alert")
	private String Alert;
	@JsonProperty("Tags")
	private String Tags;
	@JsonProperty("Group")
	private Group Group;
	@JsonProperty("Subject")
	private String Subject;
	@JsonProperty("Body")
	private String Body;
	@JsonProperty("NeedAck")
	private boolean NeedAck;
	@JsonProperty("Acknowledge")
	private boolean Acknowledge;
	@JsonProperty("Open")
	private boolean Open;
	@JsonProperty("Forgotten")
	private boolean Forgotten;
	@JsonProperty("Unevaluated")
	private boolean Unevaluated;
	@JsonProperty("LastLogTime")
	private String LastLogTime;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The Computations
	 */
	@JsonProperty("Computations")
	public Object getComputations() {
		return Computations;
	}

	/**
	 * 
	 * @param Computations
	 *            The Computations
	 */
	@JsonProperty("Computations")
	public void setComputations(Object Computations) {
		this.Computations = Computations;
	}

	/**
	 * 
	 * @return The Value
	 */
	@JsonProperty("Value")
	public long getValue() {
		return Value;
	}

	/**
	 * 
	 * @param Value
	 *            The Value
	 */
	@JsonProperty("Value")
	public void setValue(long Value) {
		this.Value = Value;
	}

	/**
	 * 
	 * @return The Expr
	 */
	@JsonProperty("Expr")
	public String getExpr() {
		return Expr;
	}

	/**
	 * 
	 * @param Expr
	 *            The Expr
	 */
	@JsonProperty("Expr")
	public void setExpr(String Expr) {
		this.Expr = Expr;
	}

	/**
	 * 
	 * @return The History
	 */
	@JsonProperty("History")
	public List<History> getHistory() {
		return History;
	}

	/**
	 * 
	 * @param History
	 *            The History
	 */
	@JsonProperty("History")
	public void setHistory(List<History> History) {
		this.History = History;
	}

	/**
	 * 
	 * @return The Touched
	 */
	@JsonProperty("Touched")
	public String getTouched() {
		return Touched;
	}

	/**
	 * 
	 * @param Touched
	 *            The Touched
	 */
	@JsonProperty("Touched")
	public void setTouched(String Touched) {
		this.Touched = Touched;
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
	 * @return The Tags
	 */
	@JsonProperty("Tags")
	public String getTags() {
		return Tags;
	}

	/**
	 * 
	 * @param Tags
	 *            The Tags
	 */
	@JsonProperty("Tags")
	public void setTags(String Tags) {
		this.Tags = Tags;
	}

	/**
	 * 
	 * @return The Group
	 */
	@JsonProperty("Group")
	public Group getGroup() {
		return Group;
	}

	/**
	 * 
	 * @param Group
	 *            The Group
	 */
	@JsonProperty("Group")
	public void setGroup(Group Group) {
		this.Group = Group;
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
	 * @return The Body
	 */
	@JsonProperty("Body")
	public String getBody() {
		return Body;
	}

	/**
	 * 
	 * @param Body
	 *            The Body
	 */
	@JsonProperty("Body")
	public void setBody(String Body) {
		this.Body = Body;
	}

	/**
	 * 
	 * @return The NeedAck
	 */
	@JsonProperty("NeedAck")
	public boolean isNeedAck() {
		return NeedAck;
	}

	/**
	 * 
	 * @param NeedAck
	 *            The NeedAck
	 */
	@JsonProperty("NeedAck")
	public void setNeedAck(boolean NeedAck) {
		this.NeedAck = NeedAck;
	}

	/**
	 * 
	 * @param Acknowledge
	 *            The Acknowledge
	 */
	@JsonProperty("Acknowledge")
	public boolean isAcknowledge() {
		return Acknowledge;
	}

	/**
	 * 
	 * @param Acknowledge
	 *            The Acknowledge
	 */
	@JsonProperty("Acknowledge")
	public void setAcknowledge(boolean acknowledge) {
		Acknowledge = acknowledge;
	}

	/**
	 * 
	 * @return The Open
	 */
	@JsonProperty("Open")
	public boolean isOpen() {
		return Open;
	}

	/**
	 * 
	 * @param Open
	 *            The Open
	 */
	@JsonProperty("Open")
	public void setOpen(boolean Open) {
		this.Open = Open;
	}

	/**
	 * 
	 * @return The Forgotten
	 */
	@JsonProperty("Forgotten")
	public boolean isForgotten() {
		return Forgotten;
	}

	/**
	 * 
	 * @param Forgotten
	 *            The Forgotten
	 */
	@JsonProperty("Forgotten")
	public void setForgotten(boolean Forgotten) {
		this.Forgotten = Forgotten;
	}

	/**
	 * 
	 * @return The Unevaluated
	 */
	@JsonProperty("Unevaluated")
	public boolean isUnevaluated() {
		return Unevaluated;
	}

	/**
	 * 
	 * @param Unevaluated
	 *            The Unevaluated
	 */
	@JsonProperty("Unevaluated")
	public void setUnevaluated(boolean Unevaluated) {
		this.Unevaluated = Unevaluated;
	}

	/**
	 * 
	 * @return The LastLogTime
	 */
	@JsonProperty("LastLogTime")
	public String getLastLogTime() {
		return LastLogTime;
	}

	/**
	 * 
	 * @param LastLogTime
	 *            The LastLogTime
	 */
	@JsonProperty("LastLogTime")
	public void setLastLogTime(String LastLogTime) {
		this.LastLogTime = LastLogTime;
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
		return "State [Computations=" + Computations + ", Value=" + Value + ", Expr=" + Expr + ", History=" + History + ", Touched=" + Touched
				+ ", Alert=" + Alert + ", Tags=" + Tags + ", Group=" + Group + ", Subject=" + Subject + ", Body=" + Body + ", NeedAck=" + NeedAck
				+ ", Acknowledge=" + Acknowledge + ", Open=" + Open + ", Forgotten=" + Forgotten + ", Unevaluated=" + Unevaluated + ", LastLogTime="
				+ LastLogTime + ", additionalProperties=" + additionalProperties + "]";
	}

}
