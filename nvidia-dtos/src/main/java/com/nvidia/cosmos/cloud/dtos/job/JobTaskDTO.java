package com.nvidia.cosmos.cloud.dtos.job;

import java.util.ArrayList;
import java.util.List;

import com.nvidia.cosmos.cloud.dtos.node.NodeDTO;

public class JobTaskDTO {

	private Long id;
	private String appId;
	private String status;
	private String image;
	private String network;
	private String host;
	private List<String> portList;
	private String startedAt;
	private String stagedAt;
	private String ipAddresses;
	private String owner;
	private String uniquename;
	private String containerName;
	private String serialNumber;
	private String dockerUri;

	private Labels labels;

	// LastTaskFailure Details
	private String message;
	private String slaveId;
	private String state;
	private String taskId;
	private String version;
	private String timestamp;

	private String jobApiType;

	private String gatewayStatus;
	private String gatewayReason;

	private String nfsVolume;
	private String localVolume;
	private String arguments;
	private String spaceSeparatedParam;
	private String containerPort;
	private String hostPort;
	private Boolean forcePullImage;
	private Boolean longRunningJob;

	private String messosSlaveUri;

	private NodeDTO nodeDato;

	public JobTaskDTO() {
		portList = new ArrayList<String>();
	}

	/**
	 * @return the nodeDato
	 */
	public NodeDTO getNodeDato() {
		return nodeDato;
	}

	/**
	 * @param nodeDato
	 *            the nodeDato to set
	 */
	public void setNodeDato(NodeDTO nodeDato) {
		this.nodeDato = nodeDato;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nfsVolume
	 */
	public String getNfsVolume() {
		return nfsVolume;
	}

	/**
	 * @return the spaceSeparatedParam
	 */
	public String getSpaceSeparatedParam() {
		return spaceSeparatedParam;
	}

	/**
	 * @param spaceSeparatedParam
	 *            the spaceSeparatedParam to set
	 */
	public void setSpaceSeparatedParam(String spaceSeparatedParam) {
		this.spaceSeparatedParam = spaceSeparatedParam;
	}

	/**
	 * @param nfsVolume
	 *            the nfsVolume to set
	 */
	public void setNfsVolume(String nfsVolume) {
		this.nfsVolume = nfsVolume;
	}

	/**
	 * @return the arguments
	 */
	public String getArguments() {
		return arguments;
	}

	/**
	 * @param arguments
	 *            the arguments to set
	 */
	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	/**
	 * @return the containerPort
	 */
	public String getContainerPort() {
		return containerPort;
	}

	/**
	 * @param containerPort
	 *            the containerPort to set
	 */
	public void setContainerPort(String containerPort) {
		this.containerPort = containerPort;
	}

	/**
	 * @return the hostPort
	 */
	public String getHostPort() {
		return hostPort;
	}

	/**
	 * @param hostPort
	 *            the hostPort to set
	 */
	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}

	/**
	 * @return the forcePullImage
	 */
	public Boolean getForcePullImage() {
		return forcePullImage;
	}

	/**
	 * @param forcePullImage
	 *            the forcePullImage to set
	 */
	public void setForcePullImage(Boolean forcePullImage) {
		this.forcePullImage = forcePullImage;
	}

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
	 * @return the portList
	 */
	public List<String> getPortList() {
		return portList;
	}

	/**
	 * @param portList
	 *            the portList to set
	 */
	public void setPortList(List<String> portList) {
		this.portList = portList;
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

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Labels getLabels() {
		return labels;
	}

	/**
	 * @return the jobApiType
	 */
	public String getJobApiType() {
		return jobApiType;
	}

	/**
	 * @param jobApiType
	 *            the jobApiType to set
	 */
	public void setJobApiType(String jobApiType) {
		this.jobApiType = jobApiType;
	}

	public void setLabels(Labels labels) {
		this.labels = labels;
	}

	public String getDockerUri() {
		return dockerUri;
	}

	public void setDockerUri(String dockerUri) {
		this.dockerUri = dockerUri;
	}

	/**
	 * @return the gatewayStatus
	 */
	public String getGatewayStatus() {
		return gatewayStatus;
	}

	/**
	 * @param gatewayStatus
	 *            the gatewayStatus to set
	 */
	public void setGatewayStatus(String gatewayStatus) {
		this.gatewayStatus = gatewayStatus;
	}

	/**
	 * @return the gatewayReason
	 */
	public String getGatewayReason() {
		return gatewayReason;
	}

	/**
	 * @param gatewayReason
	 *            the gatewayReason to set
	 */
	public void setGatewayReason(String gatewayReason) {
		this.gatewayReason = gatewayReason;
	}

	/**
	 * @return the localVolume
	 */
	public String getLocalVolume() {
		return localVolume;
	}

	/**
	 * @param localVolume
	 *            the localVolume to set
	 */
	public void setLocalVolume(String localVolume) {
		this.localVolume = localVolume;
	}

	/**
	 * @return the messosSlaveUri
	 */
	public String getMessosSlaveUri() {
		return messosSlaveUri;
	}

	/**
	 * @param messosSlaveUri
	 *            the messosSlaveUri to set
	 */
	public void setMessosSlaveUri(String messosSlaveUri) {
		this.messosSlaveUri = messosSlaveUri;
	}

	/**
	 * @return the longRunningJob
	 */
	public Boolean getLongRunningJob() {
		return longRunningJob;
	}

	/**
	 * @param longRunningJob
	 *            the longRunningJob to set
	 */
	public void setLongRunningJob(Boolean longRunningJob) {
		this.longRunningJob = longRunningJob;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobTaskDTO [id=" + id + ", appId=" + appId + ", status=" + status + ", image=" + image + ", network=" + network + ", host=" + host
				+ ", portList=" + portList + ", startedAt=" + startedAt + ", stagedAt=" + stagedAt + ", ipAddresses=" + ipAddresses + ", owner="
				+ owner + ", uniquename=" + uniquename + ", containerName=" + containerName + ", serialNumber=" + serialNumber + ", dockerUri="
				+ dockerUri + ", labels=" + labels + ", message=" + message + ", slaveId=" + slaveId + ", state=" + state + ", taskId=" + taskId
				+ ", version=" + version + ", timestamp=" + timestamp + ", jobApiType=" + jobApiType + ", gatewayStatus=" + gatewayStatus
				+ ", gatewayReason=" + gatewayReason + ", nfsVolume=" + nfsVolume + ", localVolume=" + localVolume + ", arguments=" + arguments
				+ ", spaceSeparatedParam=" + spaceSeparatedParam + ", containerPort=" + containerPort + ", hostPort=" + hostPort + ", forcePullImage="
				+ forcePullImage + ", longRunningJob=" + longRunningJob + ", messosSlaveUri=" + messosSlaveUri + ", nodeDato=" + nodeDato + "]";
	}

}
