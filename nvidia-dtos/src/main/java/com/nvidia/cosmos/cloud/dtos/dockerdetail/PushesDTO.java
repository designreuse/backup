
package com.nvidia.cosmos.cloud.dtos.dockerdetail;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PushesDTO {

    private Integer thirty_day;
    private Integer today;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The thirtyDay
     */
    @JsonProperty("thirty_day")
    public Integer getThirtyDay() {
        return thirty_day;
    }

    /**
     * 
     * @param thirtyDay
     *     The thirty_day
     */
    @JsonProperty("thirty_day")
    public void setThirtyDay(Integer thirtyDay) {
        this.thirty_day = thirtyDay;
    }

    /**
     * 
     * @return
     *     The today
     */
    @JsonProperty("today")
    public Integer getToday() {
        return today;
    }

    /**
     * 
     * @param today
     *     The today
     */
    @JsonProperty("today")
    public void setToday(Integer today) {
        this.today = today;
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
