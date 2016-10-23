
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
    "minimumHealthCapacity",
    "maximumOverCapacity"
})
public class UpgradeStrategy {

    @JsonProperty("minimumHealthCapacity")
    private Integer minimumHealthCapacity;
    @JsonProperty("maximumOverCapacity")
    private Integer maximumOverCapacity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The minimumHealthCapacity
     */
    @JsonProperty("minimumHealthCapacity")
    public Integer getMinimumHealthCapacity() {
        return minimumHealthCapacity;
    }

    /**
     * 
     * @param minimumHealthCapacity
     *     The minimumHealthCapacity
     */
    @JsonProperty("minimumHealthCapacity")
    public void setMinimumHealthCapacity(Integer minimumHealthCapacity) {
        this.minimumHealthCapacity = minimumHealthCapacity;
    }

    /**
     * 
     * @return
     *     The maximumOverCapacity
     */
    @JsonProperty("maximumOverCapacity")
    public Integer getMaximumOverCapacity() {
        return maximumOverCapacity;
    }

    /**
     * 
     * @param maximumOverCapacity
     *     The maximumOverCapacity
     */
    @JsonProperty("maximumOverCapacity")
    public void setMaximumOverCapacity(Integer maximumOverCapacity) {
        this.maximumOverCapacity = maximumOverCapacity;
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
