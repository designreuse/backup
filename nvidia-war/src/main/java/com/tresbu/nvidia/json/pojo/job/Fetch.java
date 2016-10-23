
package com.tresbu.nvidia.json.pojo.job;
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
    "uri",
    "extract",
    "executable",
    "cache"
})
public class Fetch {

    @JsonProperty("uri")
    private String uri;
    @JsonProperty("extract")
    private Boolean extract;
    @JsonProperty("executable")
    private Boolean executable;
    @JsonProperty("cache")
    private Boolean cache;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The uri
     */
    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     *     The uri
     */
    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 
     * @return
     *     The extract
     */
    @JsonProperty("extract")
    public Boolean getExtract() {
        return extract;
    }

    /**
     * 
     * @param extract
     *     The extract
     */
    @JsonProperty("extract")
    public void setExtract(Boolean extract) {
        this.extract = extract;
    }

    /**
     * 
     * @return
     *     The executable
     */
    @JsonProperty("executable")
    public Boolean getExecutable() {
        return executable;
    }

    /**
     * 
     * @param executable
     *     The executable
     */
    @JsonProperty("executable")
    public void setExecutable(Boolean executable) {
        this.executable = executable;
    }

    /**
     * 
     * @return
     *     The cache
     */
    @JsonProperty("cache")
    public Boolean getCache() {
        return cache;
    }

    /**
     * 
     * @param cache
     *     The cache
     */
    @JsonProperty("cache")
    public void setCache(Boolean cache) {
        this.cache = cache;
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
