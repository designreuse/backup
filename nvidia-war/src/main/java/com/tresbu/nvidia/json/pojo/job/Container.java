
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
    "type",
    "volumes",
    "docker"
})
public class Container {

    @JsonProperty("type")
    private String type;
    @JsonProperty("volumes")
    private List<Object> volumes = new ArrayList<Object>();
    @JsonProperty("docker")
    private Docker docker;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The volumes
     */
    @JsonProperty("volumes")
    public List<Object> getVolumes() {
        return volumes;
    }

    /**
     * 
     * @param volumes
     *     The volumes
     */
    @JsonProperty("volumes")
    public void setVolumes(List<Object> volumes) {
        this.volumes = volumes;
    }

    /**
     * 
     * @return
     *     The docker
     */
    @JsonProperty("docker")
    public Docker getDocker() {
        return docker;
    }

    /**
     * 
     * @param docker
     *     The docker
     */
    @JsonProperty("docker")
    public void setDocker(Docker docker) {
        this.docker = docker;
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
