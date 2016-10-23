
package com.nvidia.cosmos.cloud.rest.util.jobparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nvidia.cosmos.cloud.dtos.job.Container;
import com.nvidia.cosmos.cloud.dtos.job.Env;
import com.nvidia.cosmos.cloud.dtos.job.Fetch;
import com.nvidia.cosmos.cloud.dtos.job.Labels;
import com.nvidia.cosmos.cloud.dtos.job.PortDefinitions;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "cmd", "args", "user", "env", "instances", "cpus", "mem", "disk", "executor", "constraints", "uris", "fetch", "storeUrls",
		"ports", "portDefinitions", "requirePorts", "backoffSeconds", "backoffFactor", "maxLaunchDelaySeconds", "container", "healthChecks",
		"dependencies", "upgradeStrategy", "labels", "lastTaskFailure", "acceptedResourceRoles", "ipAddress", "version", "residency", "versionInfo",
		"tasksStaged", "tasksRunning", "tasksHealthy", "tasksUnhealthy", "deployments", "tasks" })
public class App {

	@JsonIgnore
	private String status;
	@JsonIgnore
	private String host;
	@JsonIgnore
	private String startedAt;
	@JsonIgnore
	private Integer port;
	@JsonProperty("id")
	private String id;
	@JsonProperty("cmd")
	private Object cmd;
	@JsonProperty("args")
	private List<String> args;
	@JsonProperty("user")
	private Object user;
	@JsonProperty("env")
	private Env env;
	@JsonProperty("instances")
	private Integer instances;
	@JsonProperty("cpus")
	private Integer cpus;
	@JsonProperty("mem")
	private Integer mem;
	@JsonProperty("disk")
	private Integer disk;
	@JsonProperty("executor")
	private String executor;
	@JsonProperty("constraints")
	private List<List<String>> constraints = new ArrayList<List<String>>();
	@JsonProperty("uris")
	private List<String> uris = new ArrayList<String>();
	@JsonProperty("fetch")
	private List<Fetch> fetch = new ArrayList<Fetch>();
	@JsonProperty("storeUrls")
	private List<Object> storeUrls = new ArrayList<Object>();
	@JsonProperty("ports")
	private List<Integer> ports = new ArrayList<Integer>();
	@JsonProperty("portDefinitions")
	private List<PortDefinitions> portDefinitions = new ArrayList<PortDefinitions>();
	@JsonProperty("requirePorts")
	private Boolean requirePorts;
	@JsonProperty("backoffSeconds")
	private Integer backoffSeconds;
	@JsonProperty("backoffFactor")
	private Double backoffFactor;
	@JsonProperty("maxLaunchDelaySeconds")
	private Integer maxLaunchDelaySeconds;
	@JsonProperty("container")
	private Container container;
	@JsonProperty("healthChecks")
	private List<Object> healthChecks = new ArrayList<Object>();
	@JsonProperty("dependencies")
	private List<Object> dependencies = new ArrayList<Object>();
	@JsonProperty("upgradeStrategy")
	private UpgradeStrategy upgradeStrategy;
	@JsonProperty("labels")
	private Labels labels;
	@JsonProperty("lastTaskFailure")
	private LastTaskFailure lastTaskFailure;
	@JsonProperty("acceptedResourceRoles")
	private Object acceptedResourceRoles;
	@JsonProperty("ipAddress")
	private Object ipAddress;
	@JsonProperty("version")
	private String version;
	@JsonProperty("residency")
	private Object residency;
	@JsonProperty("versionInfo")
	private VersionInfo versionInfo;
	@JsonProperty("tasksStaged")
	private Integer tasksStaged;
	@JsonProperty("tasksRunning")
	private Integer tasksRunning;
	@JsonProperty("tasksHealthy")
	private Integer tasksHealthy;
	@JsonProperty("tasksUnhealthy")
	private Integer tasksUnhealthy;
	@JsonProperty("deployments")
	private List<Object> deployments = new ArrayList<Object>();

	@JsonProperty("tasks")
	private List<Task> tasks = new ArrayList<Task>();

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * @return the startedAt
	 */
	@JsonIgnore
	public String getStartedAt() {
		return startedAt;
	}

	/**
	 * @param startedAt
	 *            the startedAt to set
	 */
	@JsonIgnore
	public void setStartedAt(String startedAt) {
		this.startedAt = startedAt;
	}

	/**
	 * @return the status
	 */
	@JsonIgnore
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	@JsonIgnore
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return The id
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The cmd
	 */
	@JsonProperty("cmd")
	public Object getCmd() {
		return cmd;
	}

	/**
	 * 
	 * @param cmd
	 *            The cmd
	 */
	@JsonProperty("cmd")
	public void setCmd(Object cmd) {
		this.cmd = cmd;
	}

	/**
	 * 
	 * @return The args
	 */
	@JsonProperty("args")
	public List<String> getArgs() {
		return args;
	}

	/**
	 * 
	 * @param args
	 *            The args
	 */
	@JsonProperty("args")
	public void setArgs(List<String> args) {
		this.args = args;
	}

	/**
	 * 
	 * @return The user
	 */
	@JsonProperty("user")
	public Object getUser() {
		return user;
	}

	/**
	 * 
	 * @param user
	 *            The user
	 */
	@JsonProperty("user")
	public void setUser(Object user) {
		this.user = user;
	}

	/**
	 * 
	 * @return The env
	 */
	@JsonProperty("env")
	public Env getEnv() {
		return env;
	}

	/**
	 * 
	 * @param env
	 *            The env
	 */
	@JsonProperty("env")
	public void setEnv(Env env) {
		this.env = env;
	}

	/**
	 * 
	 * @return The instances
	 */
	@JsonProperty("instances")
	public Integer getInstances() {
		return instances;
	}

	/**
	 * 
	 * @param instances
	 *            The instances
	 */
	@JsonProperty("instances")
	public void setInstances(Integer instances) {
		this.instances = instances;
	}

	/**
	 * 
	 * @return The cpus
	 */
	@JsonProperty("cpus")
	public Integer getCpus() {
		return cpus;
	}

	/**
	 * 
	 * @param cpus
	 *            The cpus
	 */
	@JsonProperty("cpus")
	public void setCpus(Integer cpus) {
		this.cpus = cpus;
	}

	/**
	 * 
	 * @return The mem
	 */
	@JsonProperty("mem")
	public Integer getMem() {
		return mem;
	}

	/**
	 * 
	 * @param mem
	 *            The mem
	 */
	@JsonProperty("mem")
	public void setMem(Integer mem) {
		this.mem = mem;
	}

	/**
	 * 
	 * @return The disk
	 */
	@JsonProperty("disk")
	public Integer getDisk() {
		return disk;
	}

	/**
	 * 
	 * @param disk
	 *            The disk
	 */
	@JsonProperty("disk")
	public void setDisk(Integer disk) {
		this.disk = disk;
	}

	/**
	 * 
	 * @return The executor
	 */
	@JsonProperty("executor")
	public String getExecutor() {
		return executor;
	}

	/**
	 * 
	 * @param executor
	 *            The executor
	 */
	@JsonProperty("executor")
	public void setExecutor(String executor) {
		this.executor = executor;
	}

	/**
	 * 
	 * @return The constraints
	 */
	@JsonProperty("constraints")
	public List<List<String>> getConstraints() {
		return constraints;
	}

	/**
	 * 
	 * @param constraints
	 *            The constraints
	 */
	@JsonProperty("constraints")
	public void setConstraints(List<List<String>> constraints) {
		this.constraints = constraints;
	}

	/**
	 * 
	 * @return The uris
	 */
	@JsonProperty("uris")
	public List<String> getUris() {
		return uris;
	}

	/**
	 * 
	 * @param uris
	 *            The uris
	 */
	@JsonProperty("uris")
	public void setUris(List<String> uris) {
		this.uris = uris;
	}

	/**
	 * 
	 * @return The fetch
	 */
	@JsonProperty("fetch")
	public List<Fetch> getFetch() {
		return fetch;
	}

	/**
	 * 
	 * @param fetch
	 *            The fetch
	 */
	@JsonProperty("fetch")
	public void setFetch(List<Fetch> fetch) {
		this.fetch = fetch;
	}

	/**
	 * 
	 * @return The storeUrls
	 */
	@JsonProperty("storeUrls")
	public List<Object> getStoreUrls() {
		return storeUrls;
	}

	/**
	 * 
	 * @param storeUrls
	 *            The storeUrls
	 */
	@JsonProperty("storeUrls")
	public void setStoreUrls(List<Object> storeUrls) {
		this.storeUrls = storeUrls;
	}

	/**
	 * 
	 * @return The ports
	 */
	@JsonProperty("ports")
	public List<Integer> getPorts() {
		return ports;
	}

	/**
	 * 
	 * @param ports
	 *            The ports
	 */
	@JsonProperty("ports")
	public void setPorts(List<Integer> ports) {
		this.ports = ports;
	}

	/**
	 * 
	 * @return The requirePorts
	 */
	@JsonProperty("requirePorts")
	public Boolean getRequirePorts() {
		return requirePorts;
	}

	/**
	 * 
	 * @param requirePorts
	 *            The requirePorts
	 */
	@JsonProperty("requirePorts")
	public void setRequirePorts(Boolean requirePorts) {
		this.requirePorts = requirePorts;
	}

	/**
	 * 
	 * @return The backoffSeconds
	 */
	@JsonProperty("backoffSeconds")
	public Integer getBackoffSeconds() {
		return backoffSeconds;
	}

	/**
	 * 
	 * @param backoffSeconds
	 *            The backoffSeconds
	 */
	@JsonProperty("backoffSeconds")
	public void setBackoffSeconds(Integer backoffSeconds) {
		this.backoffSeconds = backoffSeconds;
	}

	/**
	 * 
	 * @return The backoffFactor
	 */
	@JsonProperty("backoffFactor")
	public Double getBackoffFactor() {
		return backoffFactor;
	}

	/**
	 * 
	 * @param backoffFactor
	 *            The backoffFactor
	 */
	@JsonProperty("backoffFactor")
	public void setBackoffFactor(Double backoffFactor) {
		this.backoffFactor = backoffFactor;
	}

	/**
	 * 
	 * @return The maxLaunchDelaySeconds
	 */
	@JsonProperty("maxLaunchDelaySeconds")
	public Integer getMaxLaunchDelaySeconds() {
		return maxLaunchDelaySeconds;
	}

	/**
	 * 
	 * @param maxLaunchDelaySeconds
	 *            The maxLaunchDelaySeconds
	 */
	@JsonProperty("maxLaunchDelaySeconds")
	public void setMaxLaunchDelaySeconds(Integer maxLaunchDelaySeconds) {
		this.maxLaunchDelaySeconds = maxLaunchDelaySeconds;
	}

	/**
	 * 
	 * @return The container
	 */

	public Container getContainer() {
		return container;
	}

	/**
	 * 
	 * @param maxLaunchDelaySeconds
	 *            The container
	 */

	public void setContainer(Container container) {
		this.container = container;
	}

	/**
	 * 
	 * @return The healthChecks
	 */
	@JsonProperty("healthChecks")
	public List<Object> getHealthChecks() {
		return healthChecks;
	}

	/**
	 * 
	 * @param healthChecks
	 *            The healthChecks
	 */
	@JsonProperty("healthChecks")
	public void setHealthChecks(List<Object> healthChecks) {
		this.healthChecks = healthChecks;
	}

	/**
	 * 
	 * @return The dependencies
	 */
	@JsonProperty("dependencies")
	public List<Object> getDependencies() {
		return dependencies;
	}

	/**
	 * 
	 * @param dependencies
	 *            The dependencies
	 */
	@JsonProperty("dependencies")
	public void setDependencies(List<Object> dependencies) {
		this.dependencies = dependencies;
	}

	/**
	 * 
	 * @return The upgradeStrategy
	 */
	@JsonProperty("upgradeStrategy")
	public UpgradeStrategy getUpgradeStrategy() {
		return upgradeStrategy;
	}

	/**
	 * 
	 * @param upgradeStrategy
	 *            The upgradeStrategy
	 */
	@JsonProperty("upgradeStrategy")
	public void setUpgradeStrategy(UpgradeStrategy upgradeStrategy) {
		this.upgradeStrategy = upgradeStrategy;
	}

	/**
	 * 
	 * @return The labels
	 */
	@JsonProperty("labels")
	public Labels getLabels() {
		return labels;
	}

	/**
	 * 
	 * @param labels
	 *            The labels
	 */
	@JsonProperty("labels")
	public void setLabels(Labels labels) {
		this.labels = labels;
	}

	/**
	 * 
	 * @return The acceptedResourceRoles
	 */
	@JsonProperty("acceptedResourceRoles")
	public Object getAcceptedResourceRoles() {
		return acceptedResourceRoles;
	}

	/**
	 * @return the lastTaskFailure
	 */
	@JsonProperty("lastTaskFailure")
	public LastTaskFailure getLastTaskFailure() {
		return lastTaskFailure;
	}

	/**
	 * @param lastTaskFailure
	 *            the lastTaskFailure to set
	 */
	@JsonProperty("lastTaskFailure")
	public void setLastTaskFailure(LastTaskFailure lastTaskFailure) {
		this.lastTaskFailure = lastTaskFailure;
	}

	/**
	 * 
	 * @param acceptedResourceRoles
	 *            The acceptedResourceRoles
	 */
	@JsonProperty("acceptedResourceRoles")
	public void setAcceptedResourceRoles(Object acceptedResourceRoles) {
		this.acceptedResourceRoles = acceptedResourceRoles;
	}

	/**
	 * 
	 * @return The ipAddress
	 */
	@JsonProperty("ipAddress")
	public Object getIpAddress() {
		return ipAddress;
	}

	/**
	 * 
	 * @param ipAddress
	 *            The ipAddress
	 */
	@JsonProperty("ipAddress")
	public void setIpAddress(Object ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * 
	 * @return The version
	 */
	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	/**
	 * 
	 * @param version
	 *            The version
	 */
	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 
	 * @return The versionInfo
	 */
	@JsonProperty("versionInfo")
	public VersionInfo getVersionInfo() {
		return versionInfo;
	}

	/**
	 * 
	 * @param versionInfo
	 *            The versionInfo
	 */
	@JsonProperty("versionInfo")
	public void setVersionInfo(VersionInfo versionInfo) {
		this.versionInfo = versionInfo;
	}

	/**
	 * 
	 * @return The tasksStaged
	 */
	@JsonProperty("tasksStaged")
	public Integer getTasksStaged() {
		return tasksStaged;
	}

	/**
	 * 
	 * @param tasksStaged
	 *            The tasksStaged
	 */
	@JsonProperty("tasksStaged")
	public void setTasksStaged(Integer tasksStaged) {
		this.tasksStaged = tasksStaged;
	}

	/**
	 * 
	 * @return The tasksRunning
	 */
	@JsonProperty("tasksRunning")
	public Integer getTasksRunning() {
		return tasksRunning;
	}

	/**
	 * 
	 * @param tasksRunning
	 *            The tasksRunning
	 */
	@JsonProperty("tasksRunning")
	public void setTasksRunning(Integer tasksRunning) {
		this.tasksRunning = tasksRunning;
	}

	/**
	 * 
	 * @return The tasksHealthy
	 */
	@JsonProperty("tasksHealthy")
	public Integer getTasksHealthy() {
		return tasksHealthy;
	}

	/**
	 * 
	 * @param tasksHealthy
	 *            The tasksHealthy
	 */
	@JsonProperty("tasksHealthy")
	public void setTasksHealthy(Integer tasksHealthy) {
		this.tasksHealthy = tasksHealthy;
	}

	/**
	 * 
	 * @return The tasksUnhealthy
	 */
	@JsonProperty("tasksUnhealthy")
	public Integer getTasksUnhealthy() {
		return tasksUnhealthy;
	}

	/**
	 * 
	 * @param tasksUnhealthy
	 *            The tasksUnhealthy
	 */
	@JsonProperty("tasksUnhealthy")
	public void setTasksUnhealthy(Integer tasksUnhealthy) {
		this.tasksUnhealthy = tasksUnhealthy;
	}

	/**
	 * 
	 * @return The deployments
	 */
	@JsonProperty("deployments")
	public List<Object> getDeployments() {
		return deployments;
	}

	/**
	 * 
	 * @param deployments
	 *            The deployments
	 */
	@JsonProperty("deployments")
	public void setDeployments(List<Object> deployments) {
		this.deployments = deployments;
	}

	/**
	 * 
	 * @return The tasks
	 */
	@JsonProperty("tasks")
	public List<Task> getTasks() {
		return tasks;
	}

	/**
	 * 
	 * @param tasks
	 *            The tasks
	 */
	@JsonProperty("tasks")
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	/**
	 * @return the host
	 */
	@JsonIgnore
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	@JsonIgnore
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	@JsonIgnore
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	@JsonIgnore
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the portDefinitions
	 */
	@JsonProperty("portDefinitions")
	public List<PortDefinitions> getPortDefinitions() {
		return portDefinitions;
	}

	/**
	 * @param portDefinitions
	 *            the portDefinitions to set
	 */
	@JsonProperty("portDefinitions")
	public void setPortDefinitions(List<PortDefinitions> portDefinitions) {
		this.portDefinitions = portDefinitions;
	}

	/**
	 * @return the residency
	 */
	@JsonProperty("residency")
	public Object getResidency() {
		return residency;
	}

	/**
	 * @param residency
	 *            the residency to set
	 */
	@JsonProperty("residency")
	public void setResidency(Object residency) {
		this.residency = residency;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "App [status=" + status + ", host=" + host + ", startedAt=" + startedAt + ", port=" + port + ", id=" + id + ", cmd=" + cmd + ", args="
				+ args + ", user=" + user + ", env=" + env + ", instances=" + instances + ", cpus=" + cpus + ", mem=" + mem + ", disk=" + disk
				+ ", executor=" + executor + ", constraints=" + constraints + ", uris=" + uris + ", fetch=" + fetch + ", storeUrls=" + storeUrls
				+ ", ports=" + ports + ", portDefinitions=" + portDefinitions + ", requirePorts=" + requirePorts + ", backoffSeconds="
				+ backoffSeconds + ", backoffFactor=" + backoffFactor + ", maxLaunchDelaySeconds=" + maxLaunchDelaySeconds + ", container="
				+ container + ", healthChecks=" + healthChecks + ", dependencies=" + dependencies + ", upgradeStrategy=" + upgradeStrategy
				+ ", labels=" + labels + ", lastTaskFailure=" + lastTaskFailure + ", acceptedResourceRoles=" + acceptedResourceRoles + ", ipAddress="
				+ ipAddress + ", version=" + version + ", residency=" + residency + ", versionInfo=" + versionInfo + ", tasksStaged=" + tasksStaged
				+ ", tasksRunning=" + tasksRunning + ", tasksHealthy=" + tasksHealthy + ", tasksUnhealthy=" + tasksUnhealthy + ", deployments="
				+ deployments + ", tasks=" + tasks + ", additionalProperties=" + additionalProperties + "]";
	}

}
