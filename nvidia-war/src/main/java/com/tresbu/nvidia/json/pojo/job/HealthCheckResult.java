
package com.tresbu.nvidia.json.pojo.job;

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
    "alive",
    "consecutiveFailures",
    "firstSuccess",
    "lastFailure",
    "lastSuccess",
    "taskId"
})
public class HealthCheckResult {

    @JsonProperty("alive")
    private Boolean alive;
    @JsonProperty("consecutiveFailures")
    private Integer consecutiveFailures;
    @JsonProperty("firstSuccess")
    private String firstSuccess;
    @JsonProperty("lastFailure")
    private Object lastFailure;
    @JsonProperty("lastSuccess")
    private String lastSuccess;
    @JsonProperty("taskId")
    private String taskId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The alive
     */
    @JsonProperty("alive")
    public Boolean getAlive() {
        return alive;
    }

    /**
     * 
     * @param alive
     *     The alive
     */
    @JsonProperty("alive")
    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    /**
     * 
     * @return
     *     The consecutiveFailures
     */
    @JsonProperty("consecutiveFailures")
    public Integer getConsecutiveFailures() {
        return consecutiveFailures;
    }

    /**
     * 
     * @param consecutiveFailures
     *     The consecutiveFailures
     */
    @JsonProperty("consecutiveFailures")
    public void setConsecutiveFailures(Integer consecutiveFailures) {
        this.consecutiveFailures = consecutiveFailures;
    }

    /**
     * 
     * @return
     *     The firstSuccess
     */
    @JsonProperty("firstSuccess")
    public String getFirstSuccess() {
        return firstSuccess;
    }

    /**
     * 
     * @param firstSuccess
     *     The firstSuccess
     */
    @JsonProperty("firstSuccess")
    public void setFirstSuccess(String firstSuccess) {
        this.firstSuccess = firstSuccess;
    }

    /**
     * 
     * @return
     *     The lastFailure
     */
    @JsonProperty("lastFailure")
    public Object getLastFailure() {
        return lastFailure;
    }

    /**
     * 
     * @param lastFailure
     *     The lastFailure
     */
    @JsonProperty("lastFailure")
    public void setLastFailure(Object lastFailure) {
        this.lastFailure = lastFailure;
    }

    /**
     * 
     * @return
     *     The lastSuccess
     */
    @JsonProperty("lastSuccess")
    public String getLastSuccess() {
        return lastSuccess;
    }

    /**
     * 
     * @param lastSuccess
     *     The lastSuccess
     */
    @JsonProperty("lastSuccess")
    public void setLastSuccess(String lastSuccess) {
        this.lastSuccess = lastSuccess;
    }

    /**
     * 
     * @return
     *     The taskId
     */
    @JsonProperty("taskId")
    public String getTaskId() {
        return taskId;
    }

    /**
     * 
     * @param taskId
     *     The taskId
     */
    @JsonProperty("taskId")
    public void setTaskId(String taskId) {
        this.taskId = taskId;
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
