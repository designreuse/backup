
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
    "gracePeriodSeconds",
    "ignoreHttp1xx",
    "intervalSeconds",
    "maxConsecutiveFailures",
    "path",
    "portIndex",
    "protocol",
    "timeoutSeconds"
})
public class HealthCheck {

    @JsonProperty("gracePeriodSeconds")
    private Integer gracePeriodSeconds;
    @JsonProperty("ignoreHttp1xx")
    private Boolean ignoreHttp1xx;
    @JsonProperty("intervalSeconds")
    private Integer intervalSeconds;
    @JsonProperty("maxConsecutiveFailures")
    private Integer maxConsecutiveFailures;
    @JsonProperty("path")
    private String path;
    @JsonProperty("portIndex")
    private Integer portIndex;
    @JsonProperty("protocol")
    private String protocol;
    @JsonProperty("timeoutSeconds")
    private Integer timeoutSeconds;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The gracePeriodSeconds
     */
    @JsonProperty("gracePeriodSeconds")
    public Integer getGracePeriodSeconds() {
        return gracePeriodSeconds;
    }

    /**
     * 
     * @param gracePeriodSeconds
     *     The gracePeriodSeconds
     */
    @JsonProperty("gracePeriodSeconds")
    public void setGracePeriodSeconds(Integer gracePeriodSeconds) {
        this.gracePeriodSeconds = gracePeriodSeconds;
    }

    /**
     * 
     * @return
     *     The ignoreHttp1xx
     */
    @JsonProperty("ignoreHttp1xx")
    public Boolean getIgnoreHttp1xx() {
        return ignoreHttp1xx;
    }

    /**
     * 
     * @param ignoreHttp1xx
     *     The ignoreHttp1xx
     */
    @JsonProperty("ignoreHttp1xx")
    public void setIgnoreHttp1xx(Boolean ignoreHttp1xx) {
        this.ignoreHttp1xx = ignoreHttp1xx;
    }

    /**
     * 
     * @return
     *     The intervalSeconds
     */
    @JsonProperty("intervalSeconds")
    public Integer getIntervalSeconds() {
        return intervalSeconds;
    }

    /**
     * 
     * @param intervalSeconds
     *     The intervalSeconds
     */
    @JsonProperty("intervalSeconds")
    public void setIntervalSeconds(Integer intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
    }

    /**
     * 
     * @return
     *     The maxConsecutiveFailures
     */
    @JsonProperty("maxConsecutiveFailures")
    public Integer getMaxConsecutiveFailures() {
        return maxConsecutiveFailures;
    }

    /**
     * 
     * @param maxConsecutiveFailures
     *     The maxConsecutiveFailures
     */
    @JsonProperty("maxConsecutiveFailures")
    public void setMaxConsecutiveFailures(Integer maxConsecutiveFailures) {
        this.maxConsecutiveFailures = maxConsecutiveFailures;
    }

    /**
     * 
     * @return
     *     The path
     */
    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    /**
     * 
     * @param path
     *     The path
     */
    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 
     * @return
     *     The portIndex
     */
    @JsonProperty("portIndex")
    public Integer getPortIndex() {
        return portIndex;
    }

    /**
     * 
     * @param portIndex
     *     The portIndex
     */
    @JsonProperty("portIndex")
    public void setPortIndex(Integer portIndex) {
        this.portIndex = portIndex;
    }

    /**
     * 
     * @return
     *     The protocol
     */
    @JsonProperty("protocol")
    public String getProtocol() {
        return protocol;
    }

    /**
     * 
     * @param protocol
     *     The protocol
     */
    @JsonProperty("protocol")
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * 
     * @return
     *     The timeoutSeconds
     */
    @JsonProperty("timeoutSeconds")
    public Integer getTimeoutSeconds() {
        return timeoutSeconds;
    }

    /**
     * 
     * @param timeoutSeconds
     *     The timeoutSeconds
     */
    @JsonProperty("timeoutSeconds")
    public void setTimeoutSeconds(Integer timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
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
