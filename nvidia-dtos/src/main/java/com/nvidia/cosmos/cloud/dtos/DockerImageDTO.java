/**
 * 
 */
package com.nvidia.cosmos.cloud.dtos;

import com.nvidia.cosmos.cloud.dtos.dockerdetail.DockerImageDetailDTO;

/**
 *
 *
 */
public class DockerImageDTO implements BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DockerImageDTO() {

	}

	private boolean is_public;
	private boolean is_starred;
	private String namespace;
	private String name;
	private String description;
	/**
	 * To get each docker image detail
	 */
	private DockerImageDetailDTO dockerImageDetailDTO;

	/**
	 * @return the is_public
	 */
	public boolean isIs_public() {
		return is_public;
	}

	/**
	 * @param is_public
	 *            the is_public to set
	 */
	public void setIs_public(boolean is_public) {
		this.is_public = is_public;
	}

	/**
	 * @return the is_starred
	 */
	public boolean isIs_starred() {
		return is_starred;
	}

	/**
	 * @param is_starred
	 *            the is_starred to set
	 */
	public void setIs_starred(boolean is_starred) {
		this.is_starred = is_starred;
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
	 * @return the dockerImageDetailDTO
	 */
	public DockerImageDetailDTO getDockerImageDetailDTO() {
		return dockerImageDetailDTO;
	}

	/**
	 * @param dockerImageDetailDTO
	 *            the dockerImageDetailDTO to set
	 */
	public void setDockerImageDetailDTO(DockerImageDetailDTO dockerImageDetailDTO) {
		this.dockerImageDetailDTO = dockerImageDetailDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DockerImageDTO [is_public=" + is_public + ", is_starred=" + is_starred + ", namespace=" + namespace + ", name=" + name
				+ ", description=" + description + ", dockerImageDetailDTO=" + dockerImageDetailDTO + "]";
	}

}
