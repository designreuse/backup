package com.nvidia.cosmos.cloud.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pbatta
 *
 */
public class DockerImagesDTO implements BaseDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DockerImagesDTO(){
		
	}
	
	/**
	 * 
	 */
	List<DockerImageDTO> repositories=new ArrayList<DockerImageDTO>();

	
	public void add(DockerImageDTO dockerImageDTO){
		this.repositories.add(dockerImageDTO);
	}
	/**
	 * @return the images
	 */
	public List<DockerImageDTO> getRepositories() {
		return repositories;
	}

	/**
	 * @param images the images to set
	 */
	public void setRepositories(List<DockerImageDTO> repositories) {
		this.repositories = repositories;
	}

	public void addAllRepositories(List<DockerImageDTO> repositories){
		this.repositories.addAll(repositories);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DockerImages [repositories=" + repositories + "]";
	}
	
}
