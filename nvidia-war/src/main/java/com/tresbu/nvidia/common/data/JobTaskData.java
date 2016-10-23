package com.tresbu.nvidia.common.data;

public class JobTaskData {

	private String appId;
	private String status;
	private String image;
	private String network;
	private String host;
	private Integer port;
	private String startedAt;
	private String stagedAt;
	private String ipAddresses;
	private String owner;
	private String uniquename;
	private String containerName;

	// LastTaskFailure Details
	private String message;
	private String slaveId;
	private String state;
	private String taskId;
	private String version;
	private String timestamp;

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the network
	 */
	public String getNetwork() {
		return network;
	}

	/**
	 * @param network
	 *            the network to set
	 */
	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the startedAt
	 */
	public String getStartedAt() {
		return startedAt;
	}

	/**
	 * @param startedAt
	 *            the startedAt to set
	 */
	public void setStartedAt(String startedAt) {
		this.startedAt = startedAt;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the slaveId
	 */
	public String getSlaveId() {
		return slaveId;
	}

	/**
	 * @param slaveId
	 *            the slaveId to set
	 */
	public void setSlaveId(String slaveId) {
		this.slaveId = slaveId;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the stagedAt
	 */
	public String getStagedAt() {
		return stagedAt;
	}

	/**
	 * @param stagedAt
	 *            the stagedAt to set
	 */
	public void setStagedAt(String stagedAt) {
		this.stagedAt = stagedAt;
	}

	/**
	 * @return the ipAddresses
	 */
	public String getIpAddresses() {
		return ipAddresses;
	}

	/**
	 * @param ipAddresses
	 *            the ipAddresses to set
	 */
	public void setIpAddresses(String ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	/**
	 * @return the owner
	 */

	public String getUniquename() {
		return uniquename;
	}

	/**
	 * @param owner
	 *            the uniquename to set
	 */
	
	public void setUniquename(String uniquename) {
		this.uniquename = uniquename;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return "JobTaskData [appId=" + appId + ", status=" + status + ", image=" + image + ", network=" + network
				+ ", host=" + host + ", port=" + port + ", startedAt=" + startedAt + ", stagedAt=" + stagedAt
				+ ", ipAddresses=" + ipAddresses + ", owner=" + owner + ", uniquename=" + uniquename + ", message="
				+ message + ", slaveId=" + slaveId + ", state=" + state + ", taskId=" + taskId + ", version=" + version
				+ ", timestamp=" + timestamp + "]";
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
	
}
