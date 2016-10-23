
package com.nvidia.cosmos.cloud.rest.util.jobparser;

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
    "appId",
    "host",
    "message",
    "slaveId",
    "state",
    "taskId",
    "timestamp",
    "version"
})
public class LastTaskFailure {

    @JsonProperty("appId")
    private String appId;
    @JsonProperty("host")
    private String host;
    @JsonProperty("message")
    private String message;
    @JsonProperty("slaveId")
    private String slaveId;
    @JsonProperty("state")
    private String state;
    @JsonProperty("taskId")
    private String taskId;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("version")
    private String version;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The appId
     */
    @JsonProperty("appId")
    public String getAppId() {
        return appId;
    }

    /**
     * 
     * @param appId
     *     The appId
     */
    @JsonProperty("appId")
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 
     * @return
     *     The host
     */
    @JsonProperty("host")
    public String getHost() {
        return host;
    }

    /**
     * 
     * @param host
     *     The host
     */
    @JsonProperty("host")
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 
     * @return
     *     The message
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The slaveId
     */
    @JsonProperty("slaveId")
    public String getSlaveId() {
        return slaveId;
    }

    /**
     * 
     * @param slaveId
     *     The slaveId
     */
    @JsonProperty("slaveId")
    public void setSlaveId(String slaveId) {
        this.slaveId = slaveId;
    }

    /**
     * 
     * @return
     *     The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
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

    /**
     * 
     * @return
     *     The timestamp
     */
    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param timestamp
     *     The timestamp
     */
    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 
     * @return
     *     The version
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
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
