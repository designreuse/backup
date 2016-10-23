
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
    "key",
    "serial_number",
    "node_status"
})
public class Node {

    @JsonProperty("key")
    private String key;
    @JsonProperty("serial_number")
    private String serialNumber;
    @JsonProperty("node_status")
    private NodeStatus nodeStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The key
     */
    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    /**
     * 
     * @param key
     *     The key
     */
    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 
     * @return
     *     The serialNumber
     */
    @JsonProperty("serial_number")
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * 
     * @param serialNumber
     *     The serial_number
     */
    @JsonProperty("serial_number")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 
     * @return
     *     The nodeStatus
     */
    @JsonProperty("node_status")
    public NodeStatus getNodeStatus() {
        return nodeStatus;
    }

    /**
     * 
     * @param nodeStatus
     *     The node_status
     */
    @JsonProperty("node_status")
    public void setNodeStatus(NodeStatus nodeStatus) {
        this.nodeStatus = nodeStatus;
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
