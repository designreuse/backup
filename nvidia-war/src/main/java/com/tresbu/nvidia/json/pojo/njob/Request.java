/**
 * 
 */
package com.tresbu.nvidia.json.pojo.njob;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pbatta
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Request {
	public Request() {

	}

	@JsonProperty("id")
	private String id;

	@JsonProperty("fetch")
	private List<Fetch> fetch;

	@JsonProperty("portDefinitions")
	private List<PortDefinitions> portDefinitions;

	@JsonProperty("cpus")
	private int cpus;

	@JsonProperty("cmd")
	private String cmd;

	@JsonProperty("uris")
	private String[] uris;

	@JsonProperty("args")
	private String[] args;

	@JsonProperty("container")
	private Container container;

	@JsonProperty("disk")
	private int disk;

	@JsonProperty("mem")
	private int mem;

	@JsonProperty("instances")
	private int instances;

	@JsonProperty("constraints")
	private List<List<String>> constraints;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the fetch
	 */
	public List<Fetch> getFetch() {
		return fetch;
	}

	/**
	 * @param fetch
	 *            the fetch to set
	 */
	public void setFetch(List<Fetch> fetch) {
		this.fetch = fetch;
	}

	/**
	 * @return the portDefinitions
	 */
	public List<PortDefinitions> getPortDefinitions() {
		return portDefinitions;
	}

	/**
	 * @param portDefinitions
	 *            the portDefinitions to set
	 */
	public void setPortDefinitions(List<PortDefinitions> portDefinitions) {
		this.portDefinitions = portDefinitions;
	}

	/**
	 * @return the cpus
	 */
	public int getCpus() {
		return cpus;
	}

	/**
	 * @param cpus
	 *            the cpus to set
	 */
	public void setCpus(int cpus) {
		this.cpus = cpus;
	}

	/**
	 * @return the cmd
	 */
	public String getCmd() {
		return cmd;
	}

	/**
	 * @param cmd
	 *            the cmd to set
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	/**
	 * @return the uris
	 */
	public String[] getUris() {
		return uris;
	}

	/**
	 * @param uris
	 *            the uris to set
	 */
	public void setUris(String[] uris) {
		this.uris = uris;
	}

	/**
	 * @return the disk
	 */
	public int getDisk() {
		return disk;
	}

	/**
	 * @param disk
	 *            the disk to set
	 */
	public void setDisk(int disk) {
		this.disk = disk;
	}

	/**
	 * @return the mem
	 */
	public int getMem() {
		return mem;
	}

	/**
	 * @param mem
	 *            the mem to set
	 */
	public void setMem(int mem) {
		this.mem = mem;
	}

	/**
	 * @return the instances
	 */
	public int getInstances() {
		return instances;
	}

	/**
	 * @param instances
	 *            the instances to set
	 */
	public void setInstances(int instances) {
		this.instances = instances;
	}

	/**
	 * @return the container
	 */
	public Container getContainer() {
		return container;
	}

	/**
	 * @param container
	 *            the container to set
	 */
	public void setContainer(Container container) {
		this.container = container;
	}

	/**
	 * @return the constraints
	 */
	public List<List<String>> getConstraints() {
		return constraints;
	}

	/**
	 * @param constraints
	 *            the constraints to set
	 */
	public void setConstraints(List<List<String>> constraints) {
		this.constraints = constraints;
	}

	/**
	 * @return the args
	 */
	public String[] getArgs() {
		return args;
	}

	/**
	 * @param args
	 *            the args to set
	 */
	public void setArgs(String[] args) {
		this.args = args;
	}

	
	
	/**
	 * @param id
	 * @param fetch
	 * @param portDefinitions
	 * @param cpus
	 * @param cmd
	 * @param uris
	 * @param args
	 * @param container
	 * @param disk
	 * @param mem
	 * @param instances
	 * @param constraints
	 */
	public Request(String id, List<Fetch> fetch, List<PortDefinitions> portDefinitions, int cpus, String cmd, String[] uris, String[] args, Container container, int disk, int mem,
			int instances, List<List<String>> constraints) {
		super();
		this.id = id;
		this.fetch = fetch;
		this.portDefinitions = portDefinitions;
		this.cpus = cpus;
		this.cmd = cmd;
		this.uris = uris;
		this.args = args;
		this.container = container;
		this.disk = disk;
		this.mem = mem;
		this.instances = instances;
		this.constraints = constraints;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Request [id=" + id + ", fetch=" + fetch + ", portDefinitions=" + portDefinitions + ", cpus=" + cpus + ", cmd=" + cmd + ", uris=" + Arrays.toString(uris) + ", args="
				+ Arrays.toString(args) + ", container=" + container + ", disk=" + disk + ", mem=" + mem + ", instances=" + instances + ", constraints=" + constraints + "]";
	}
	
	

}
