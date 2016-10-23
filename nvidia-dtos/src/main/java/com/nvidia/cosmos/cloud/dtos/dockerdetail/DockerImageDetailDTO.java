
package com.nvidia.cosmos.cloud.dtos.dockerdetail;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DockerImageDetailDTO {

	private String description;
	private TagsDTO tags;
	private Boolean is_public;
	private Boolean is_starred;
	private StatsDTO stats;
	private String name;
	private String namespace;
	private Boolean is_organization;
	private Boolean can_write;
	private String status_token;
	private Boolean can_admin;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the tags
	 */
	public TagsDTO getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(TagsDTO tags) {
		this.tags = tags;
	}

	/**
	 * @return the is_public
	 */
	public Boolean getIs_public() {
		return is_public;
	}

	/**
	 * @param is_public
	 *            the is_public to set
	 */
	public void setIs_public(Boolean is_public) {
		this.is_public = is_public;
	}

	/**
	 * @return the is_starred
	 */
	public Boolean getIs_starred() {
		return is_starred;
	}

	/**
	 * @param is_starred
	 *            the is_starred to set
	 */
	public void setIs_starred(Boolean is_starred) {
		this.is_starred = is_starred;
	}

	/**
	 * @return the stats
	 */
	public StatsDTO getStats() {
		return stats;
	}

	/**
	 * @param stats
	 *            the stats to set
	 */
	public void setStats(StatsDTO stats) {
		this.stats = stats;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the namespace
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * @param namespace
	 *            the namespace to set
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	/**
	 * @return the is_organization
	 */
	public Boolean getIs_organization() {
		return is_organization;
	}

	/**
	 * @param is_organization
	 *            the is_organization to set
	 */
	public void setIs_organization(Boolean is_organization) {
		this.is_organization = is_organization;
	}

	/**
	 * @return the can_write
	 */
	public Boolean getCan_write() {
		return can_write;
	}

	/**
	 * @param can_write
	 *            the can_write to set
	 */
	public void setCan_write(Boolean can_write) {
		this.can_write = can_write;
	}

	/**
	 * @return the status_token
	 */
	public String getStatus_token() {
		return status_token;
	}

	/**
	 * @param status_token
	 *            the status_token to set
	 */
	public void setStatus_token(String status_token) {
		this.status_token = status_token;
	}

	/**
	 * @return the can_admin
	 */
	public Boolean getCan_admin() {
		return can_admin;
	}

	/**
	 * @param can_admin
	 *            the can_admin to set
	 */
	public void setCan_admin(Boolean can_admin) {
		this.can_admin = can_admin;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DockerImageDetailDTO [description=" + description + ", tags=" + tags + ", is_public=" + is_public + ", is_starred=" + is_starred
				+ ", stats=" + stats + ", name=" + name + ", namespace=" + namespace + ", is_organization=" + is_organization + ", can_write="
				+ can_write + ", status_token=" + status_token + ", can_admin=" + can_admin + ", additionalProperties=" + additionalProperties + "]";
	}

}
