
package com.tresbu.nvidia.json.pojo.job;
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
    "image",
    "network",
    "portMappings",
    "privileged",
    "parameters",
    "forcePullImage"
})
public class Docker {

    @JsonProperty("image")
    private String image;
    @JsonProperty("network")
    private String network;
    @JsonProperty("portMappings")
    private List<PortMapping> portMappings = new ArrayList<PortMapping>();
    @JsonProperty("privileged")
    private Boolean privileged;
    @JsonProperty("parameters")
    private List<Object> parameters = new ArrayList<Object>();
    @JsonProperty("forcePullImage")
    private Boolean forcePullImage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The image
     */
    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The network
     */
    @JsonProperty("network")
    public String getNetwork() {
        return network;
    }

    /**
     * 
     * @param network
     *     The network
     */
    @JsonProperty("network")
    public void setNetwork(String network) {
        this.network = network;
    }

    /**
     * 
     * @return
     *     The portMappings
     */
    @JsonProperty("portMappings")
    public List<PortMapping> getPortMappings() {
        return portMappings;
    }

    /**
     * 
     * @param portMappings
     *     The portMappings
     */
    @JsonProperty("portMappings")
    public void setPortMappings(List<PortMapping> portMappings) {
        this.portMappings = portMappings;
    }

    /**
     * 
     * @return
     *     The privileged
     */
    @JsonProperty("privileged")
    public Boolean getPrivileged() {
        return privileged;
    }

    /**
     * 
     * @param privileged
     *     The privileged
     */
    @JsonProperty("privileged")
    public void setPrivileged(Boolean privileged) {
        this.privileged = privileged;
    }

    /**
     * 
     * @return
     *     The parameters
     */
    @JsonProperty("parameters")
    public List<Object> getParameters() {
        return parameters;
    }

    /**
     * 
     * @param parameters
     *     The parameters
     */
    @JsonProperty("parameters")
    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    /**
     * 
     * @return
     *     The forcePullImage
     */
    @JsonProperty("forcePullImage")
    public Boolean getForcePullImage() {
        return forcePullImage;
    }

    /**
     * 
     * @param forcePullImage
     *     The forcePullImage
     */
    @JsonProperty("forcePullImage")
    public void setForcePullImage(Boolean forcePullImage) {
        this.forcePullImage = forcePullImage;
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
