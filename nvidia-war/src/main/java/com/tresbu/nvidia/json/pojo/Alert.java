
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
@JsonPropertyOrder({
    "Groups",
    "TimeAndDate",
    "FailingAlerts",
    "UnclosedErrors"
})
public class Alert {

    @JsonProperty("Groups")
    private Groups Groups;
    @JsonProperty("TimeAndDate")
    private List<Long> TimeAndDate = new ArrayList<Long>();
    @JsonProperty("FailingAlerts")
    private long FailingAlerts;
    @JsonProperty("UnclosedErrors")
    private long UnclosedErrors;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Groups
     */
    @JsonProperty("Groups")
    public Groups getGroups() {
        return Groups;
    }

    /**
     * 
     * @param Groups
     *     The Groups
     */
    @JsonProperty("Groups")
    public void setGroups(Groups Groups) {
        this.Groups = Groups;
    }

    /**
     * 
     * @return
     *     The TimeAndDate
     */
    @JsonProperty("TimeAndDate")
    public List<Long> getTimeAndDate() {
        return TimeAndDate;
    }

    /**
     * 
     * @param TimeAndDate
     *     The TimeAndDate
     */
    @JsonProperty("TimeAndDate")
    public void setTimeAndDate(List<Long> TimeAndDate) {
        this.TimeAndDate = TimeAndDate;
    }

    /**
     * 
     * @return
     *     The FailingAlerts
     */
    @JsonProperty("FailingAlerts")
    public long getFailingAlerts() {
        return FailingAlerts;
    }

    /**
     * 
     * @param FailingAlerts
     *     The FailingAlerts
     */
    @JsonProperty("FailingAlerts")
    public void setFailingAlerts(long FailingAlerts) {
        this.FailingAlerts = FailingAlerts;
    }

    /**
     * 
     * @return
     *     The UnclosedErrors
     */
    @JsonProperty("UnclosedErrors")
    public long getUnclosedErrors() {
        return UnclosedErrors;
    }

    /**
     * 
     * @param UnclosedErrors
     *     The UnclosedErrors
     */
    @JsonProperty("UnclosedErrors")
    public void setUnclosedErrors(long UnclosedErrors) {
        this.UnclosedErrors = UnclosedErrors;
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
		return "Alert [Groups=" + Groups + ", TimeAndDate=" + TimeAndDate + ", FailingAlerts=" + FailingAlerts + ", UnclosedErrors=" + UnclosedErrors
				+ ", additionalProperties=" + additionalProperties + "]";
	}

    
}
