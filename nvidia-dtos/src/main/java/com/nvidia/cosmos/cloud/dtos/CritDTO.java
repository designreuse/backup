
package com.nvidia.cosmos.cloud.dtos;

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
    "Computations",
    "Value",
    "Group",
    "Expr"
})
public class CritDTO {

    @JsonProperty("Computations")
    private Object Computations;
    @JsonProperty("Value")
    private long Value;
    @JsonProperty("Group")
    private Object Group;
    @JsonProperty("Expr")
    private String Expr;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Computations
     */
    @JsonProperty("Computations")
    public Object getComputations() {
        return Computations;
    }

    /**
     * 
     * @param Computations
     *     The Computations
     */
    @JsonProperty("Computations")
    public void setComputations(Object Computations) {
        this.Computations = Computations;
    }

    /**
     * 
     * @return
     *     The Value
     */
    @JsonProperty("Value")
    public long getValue() {
        return Value;
    }

    /**
     * 
     * @param Value
     *     The Value
     */
    @JsonProperty("Value")
    public void setValue(long Value) {
        this.Value = Value;
    }

    /**
     * 
     * @return
     *     The Group
     */
    @JsonProperty("Group")
    public Object getGroup() {
        return Group;
    }

    /**
     * 
     * @param Group
     *     The Group
     */
    @JsonProperty("Group")
    public void setGroup(Object Group) {
        this.Group = Group;
    }

    /**
     * 
     * @return
     *     The Expr
     */
    @JsonProperty("Expr")
    public String getExpr() {
        return Expr;
    }

    /**
     * 
     * @param Expr
     *     The Expr
     */
    @JsonProperty("Expr")
    public void setExpr(String Expr) {
        this.Expr = Expr;
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
