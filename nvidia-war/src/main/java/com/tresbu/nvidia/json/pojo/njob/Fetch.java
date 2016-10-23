package com.tresbu.nvidia.json.pojo.njob;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fetch
{
	@JsonProperty("cache")
    private String cache;

	@JsonProperty("executable")
    private String executable;

	@JsonProperty("uri")
    private String uri;

	@JsonProperty("extract")
    private String extract;

    public String getCache ()
    {
        return cache;
    }

    public void setCache (String cache)
    {
        this.cache = cache;
    }

    public String getExecutable ()
    {
        return executable;
    }

    public void setExecutable (String executable)
    {
        this.executable = executable;
    }

    public String getUri ()
    {
        return uri;
    }

    public void setUri (String uri)
    {
        this.uri = uri;
    }

    public String getExtract ()
    {
        return extract;
    }

    public void setExtract (String extract)
    {
        this.extract = extract;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cache = "+cache+", executable = "+executable+", uri = "+uri+", extract = "+extract+"]";
    }
}