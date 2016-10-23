
package com.tresbu.nvidia.json.pojo;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Warn",
    "Crit",
    "Status",
    "Time",
    "Unevaluated",
    "IncidentId"
})
public class History {

    @JsonProperty("Warn")
    private Object Warn;
    @JsonProperty("Crit")
    private Crit Crit;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Time")
    private String Time;
    @JsonProperty("Unevaluated")
    private boolean Unevaluated;
    @JsonProperty("IncidentId")
    private long IncidentId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Warn
     */
    @JsonProperty("Warn")
    public Object getWarn() {
        return Warn;
    }

    /**
     * 
     * @param Warn
     *     The Warn
     */
    @JsonProperty("Warn")
    public void setWarn(Object Warn) {
        this.Warn = Warn;
    }

    /**
     * 
     * @return
     *     The Crit
     */
    @JsonProperty("Crit")
    public Crit getCrit() {
        return Crit;
    }

    /**
     * 
     * @param Crit
     *     The Crit
     */
    @JsonProperty("Crit")
    public void setCrit(Crit Crit) {
        this.Crit = Crit;
    }

    /**
     * 
     * @return
     *     The Status
     */
    @JsonProperty("Status")
    public String getStatus() {
        return Status;
    }

    /**
     * 
     * @param Status
     *     The Status
     */
    @JsonProperty("Status")
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     * 
     * @return
     *     The Time
     */
    @JsonProperty("Time")
    public String getTime() {
        return Time;
    }

    /**
     * 
     * @param Time
     *     The Time
     */
    @JsonProperty("Time")
    public void setTime(String Time) {
        this.Time = Time;
    }

    /**
     * 
     * @return
     *     The Unevaluated
     */
    @JsonProperty("Unevaluated")
    public boolean isUnevaluated() {
        return Unevaluated;
    }

    /**
     * 
     * @param Unevaluated
     *     The Unevaluated
     */
    @JsonProperty("Unevaluated")
    public void setUnevaluated(boolean Unevaluated) {
        this.Unevaluated = Unevaluated;
    }

    /**
     * 
     * @return
     *     The IncidentId
     */
    @JsonProperty("IncidentId")
    public long getIncidentId() {
        return IncidentId;
    }

    /**
     * 
     * @param IncidentId
     *     The IncidentId
     */
    @JsonProperty("IncidentId")
    public void setIncidentId(long IncidentId) {
        this.IncidentId = IncidentId;
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
