
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
    "NeedAck",
    "Acknowledged"
})
public class Groups {

    @JsonProperty("NeedAck")
    private List<NeedAck> NeedAck = new ArrayList<NeedAck>();
    @JsonProperty("Acknowledged")
    private List<Acknowledged> Acknowledged = new ArrayList<Acknowledged>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The NeedAck
     */
    @JsonProperty("NeedAck")
    public List<NeedAck> getNeedAck() {
        return NeedAck;
    }

    /**
     * 
     * @param NeedAck
     *     The NeedAck
     */
    @JsonProperty("NeedAck")
    public void setNeedAck(List<NeedAck> NeedAck) {
        this.NeedAck = NeedAck;
    }

    

    /**
     * 
     * @return
     *     The Acknowledged
     */
    @JsonProperty("Acknowledged")
    public List<Acknowledged> getAcknowledged() {
        return Acknowledged;
    }

    /**
     * 
     * @param Acknowledged
     *     The Acknowledged
     */
    @JsonProperty("Acknowledged")
    public void setAcknowledged(List<Acknowledged> Acknowledged) {
        this.Acknowledged = Acknowledged;
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
