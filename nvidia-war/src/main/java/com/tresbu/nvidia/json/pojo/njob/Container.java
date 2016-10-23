/**
 * 
 */
package com.tresbu.nvidia.json.pojo.njob;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pbatta
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Container {

	@JsonProperty("docker")
	private Docker docker;

	@JsonProperty("volumes")
	private String[] volumes;

	@JsonProperty("type")
	private String type;

	public Docker getDocker() {
		return docker;
	}

	public void setDocker(Docker docker) {
		this.docker = docker;
	}

	@JsonProperty("volumes")
	public String[] getVolumes() {
		return volumes;
	}

	@JsonProperty("volumes")
	public void setVolumes(String[] volumes) {
		this.volumes = volumes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Container [docker=" + docker + ", volumes=" + Arrays.toString(volumes) + ", type=" + type + "]";
	}

}