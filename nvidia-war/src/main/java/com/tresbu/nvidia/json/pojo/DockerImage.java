/**
 * 
 */
package com.tresbu.nvidia.json.pojo;

/**
 * @author pbatta
 *
 */
public class DockerImage {
	public DockerImage(){
		
	}
	boolean is_public;
	boolean is_starred;
	String namespace;
	String name;
	String description;
	/**
	 * @return the is_public
	 */
	public boolean isIs_public() {
		return is_public;
	}
	/**
	 * @param is_public the is_public to set
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
	 * @param is_starred the is_starred to set
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
	 * @param namespace the namespace to set
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
	 * @param name the name to set
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DockerImage [is_public=" + is_public + ", is_starred=" + is_starred + ", namespace=" + namespace
				+ ", name=" + name + ", description=" + description + "]";
	}
	
}
