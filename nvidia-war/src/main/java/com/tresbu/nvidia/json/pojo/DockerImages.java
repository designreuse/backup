package com.tresbu.nvidia.json.pojo;

import java.util.List;

public class DockerImages {
	public DockerImages(){
		
	}
	
	List<DockerImage> repositories;

	/**
	 * @return the repositories
	 */
	public List<DockerImage> getRepositories() {
		return repositories;
	}

	/**
	 * @param repositories the repositories to set
	 */
	public void setRepositories(List<DockerImage> repositories) {
		this.repositories = repositories;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DockerImages [repositories=" + repositories + "]";
	}

	
}
