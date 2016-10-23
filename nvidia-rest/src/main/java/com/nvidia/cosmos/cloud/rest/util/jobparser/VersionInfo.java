
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
    "lastScalingAt",
    "lastConfigChangeAt"
})
public class VersionInfo {

    @JsonProperty("lastScalingAt")
    private String lastScalingAt;
    @JsonProperty("lastConfigChangeAt")
    private String lastConfigChangeAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The lastScalingAt
     */
    @JsonProperty("lastScalingAt")
    public String getLastScalingAt() {
        return lastScalingAt;
    }

    /**
     * 
     * @param lastScalingAt
     *     The lastScalingAt
     */
    @JsonProperty("lastScalingAt")
    public void setLastScalingAt(String lastScalingAt) {
        this.lastScalingAt = lastScalingAt;
    }

    /**
     * 
     * @return
     *     The lastConfigChangeAt
     */
    @JsonProperty("lastConfigChangeAt")
    public String getLastConfigChangeAt() {
        return lastConfigChangeAt;
    }

    /**
     * 
     * @param lastConfigChangeAt
     *     The lastConfigChangeAt
     */
    @JsonProperty("lastConfigChangeAt")
    public void setLastConfigChangeAt(String lastConfigChangeAt) {
        this.lastConfigChangeAt = lastConfigChangeAt;
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
