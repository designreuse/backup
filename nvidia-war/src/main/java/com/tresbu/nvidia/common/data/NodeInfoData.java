package com.tresbu.nvidia.common.data;

public class NodeInfoData {

	private String serialId;
	private String nodeKey;
	private String clusterId;
	private String name;
	private String ipAddress;
	private String status;
	private String tags;
	private String createdTime;
	private String lastRestartedonTime;
	private String memory;
	private String diskSpace;
	private String biosVersion;
	private String mode;
	private String swVersion;
	private String fwVersion;
	private String ipmi;
	private String startTime;
	private String isLeader;
	private String subnet;
	private String clusterGroup;
	private String nodenetworkinformation;
	private String gateway;
	private Integer gpuConfiguration;

	public NodeInfoData() {
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getLastRestartedonTime() {
		return lastRestartedonTime;
	}

	public void setLastRestartedonTime(String lastRestartedonTime) {
		this.lastRestartedonTime = lastRestartedonTime;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getDiskSpace() {
		return diskSpace;
	}

	public void setDiskSpace(String diskSpace) {
		this.diskSpace = diskSpace;
	}

	public String getBiosVersion() {
		return biosVersion;
	}

	public void setBiosVersion(String biosVersion) {
		this.biosVersion = biosVersion;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getSwVersion() {
		return swVersion;
	}

	public void setSwVersion(String swVersion) {
		this.swVersion = swVersion;
	}

	public String getFwVersion() {
		return fwVersion;
	}

	public void setFwVersion(String fwVersion) {
		this.fwVersion = fwVersion;
	}

	public String getIpmi() {
		return ipmi;
	}

	public void setIpmi(String ipmi) {
		this.ipmi = ipmi;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}

	public String getSubnet() {
		return subnet;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	public String getClusterGroup() {
		return clusterGroup;
	}

	public void setClusterGroup(String clusterGroup) {
		this.clusterGroup = clusterGroup;
	}

	public String getNodenetworkinformation() {
		return nodenetworkinformation;
	}

	public void setNodenetworkinformation(String nodenetworkinformation) {
		this.nodenetworkinformation = nodenetworkinformation;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getNodeKey() {
		return nodeKey;
	}

	public void setNodeKey(String nodeKey) {
		this.nodeKey = nodeKey;
	}

	/**
	 * @return the gpuConfiguration
	 */
	public Integer getGpuConfiguration() {
		return gpuConfiguration;
	}

	/**
	 * @param gpuConfiguration
	 *            the gpuConfiguration to set
	 */
	public void setGpuConfiguration(Integer gpuConfiguration) {
		this.gpuConfiguration = gpuConfiguration;
	}

}
