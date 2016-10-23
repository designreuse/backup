
package com.nvidia.cosmos.cloud.dtos.dockerdetail;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class StatsDTO {

	private PullsDTO pulls;
	private PushesDTO pushes;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The pulls
	 */
	public PullsDTO getPulls() {
		return pulls;
	}

	/**
	 * 
	 * @param pulls
	 *            The pulls
	 */
	public void setPulls(PullsDTO pulls) {
		this.pulls = pulls;
	}

	/**
	 * 
	 * @return The pushesDTO
	 */
	public PushesDTO getPushes() {
		return pushes;
	}

	/**
	 * 
	 * @param pushes
	 *            The pushes
	 */
	public void setPushes(PushesDTO pushes) {
		this.pushes = pushes;
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
