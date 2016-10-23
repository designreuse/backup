/**
 * 
 */
package com.nvidia.cosmos.cloud.dtos.job;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pbatta
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"$$hashKey"})
public class Parameters
{
	@JsonProperty("value")
    private String value;

	@JsonProperty("key")
    private String key;

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [value = "+value+", key = "+key+"]";
    }
}