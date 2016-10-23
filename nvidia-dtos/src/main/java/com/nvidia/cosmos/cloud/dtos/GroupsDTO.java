
package com.nvidia.cosmos.cloud.dtos;
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
public class GroupsDTO {

    @JsonProperty("NeedAck")
    private List<NeedAckDTO> NeedAck = new ArrayList<NeedAckDTO>();
    @JsonProperty("Acknowledged")
    private List<AcknowledgedDTO> Acknowledged = new ArrayList<AcknowledgedDTO>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The NeedAck
     */
    @JsonProperty("NeedAck")
    public List<NeedAckDTO> getNeedAck() {
        return NeedAck;
    }

    /**
     * 
     * @param NeedAck
     *     The NeedAck
     */
    @JsonProperty("NeedAck")
    public void setNeedAck(List<NeedAckDTO> NeedAck) {
        this.NeedAck = NeedAck;
    }

    

    /**
     * 
     * @return
     *     The Acknowledged
     */
    @JsonProperty("Acknowledged")
    public List<AcknowledgedDTO> getAcknowledged() {
        return Acknowledged;
    }

    /**
     * 
     * @param Acknowledged
     *     The Acknowledged
     */
    @JsonProperty("Acknowledged")
    public void setAcknowledged(List<AcknowledgedDTO> Acknowledged) {
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
