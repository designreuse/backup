
package com.nvidia.cosmos.cloud.rest.util.jobparser;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "app" })
public class JobDetails {

	@JsonProperty("app")
	private App app;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The app
	 */
	@JsonProperty("app")
	public App getApp() {
		return app;
	}

	/**
	 * 
	 * @param app
	 *            The app
	 */
	@JsonProperty("app")
	public void setApp(App app) {
		this.app = app;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "JobDetails [app=" + app + ", additionalProperties=" + additionalProperties + "]";
	}

}
