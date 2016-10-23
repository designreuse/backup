
package com.tresbu.nvidia.json.pojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ 
	"cloud_managed", 
	"sw_version", 
	"is_leader", 
	"tags", 
	"eula_accepted", 
	"cloud_status", 
	"clusterId", 
	"serial_number", 
	"fw_version",
	"first_boot", 
	"gpu_configuration", 
	"node_id", 
	"licenceKey", 
	"cluster_group", 
	"bios_version", 
	"cloud_group", 
	"serialid", 
	"ip_address",
	"model_name", 
	"nodeName", 
	"created_time", 
	"disk_space", 
	"status", 
	"start_time", 
	"Mode",
	"gateway",
	"subnet",
	"memory", 
	"last_restarted_on_time",
	"ipmi",
	"key",
	"upgrade_on_boot"})

public class NodeStatus {

	@JsonProperty("cloud_managed")
	private String cloudManaged;
	@JsonProperty("sw_version")
	private String swVersion;
	@JsonProperty("is_leader")
	private String isLeader;
	@JsonProperty("tags")
	private String tags;
	@JsonProperty("eula_accepted")
	private String eulaAccepted;
	@JsonProperty("cloud_status")
	private String cloudStatus;
	@JsonProperty("clusterId")
	private String clusterId;
	@JsonProperty("serial_number")
	private String serialNumber;
	@JsonProperty("fw_version")
	private String fwVersion;
	@JsonProperty("first_boot")
	private String firstBoot;
	@JsonProperty("gpu_configuration")
	private String gpuConfiguration;
	@JsonProperty("node_id")
	private String nodeId;
	@JsonProperty("licenceKey")
	private String licenceKey;
	@JsonProperty("cluster_group")
	private String clusterGroup;
	@JsonProperty("bios_version")
	private String biosVersion;
	@JsonProperty("cloud_group")
	private String cloudGroup;
	@JsonProperty("serialid")
	private String serialid;
	@JsonProperty("ip_address")
	private String ipAddress;
	@JsonProperty("model_name")
	private String modelName;
	@JsonProperty("nodeName")
	private String nodeName;
	@JsonProperty("created_time")
	private String createdTime;
	@JsonProperty("disk_space")
	private String diskSpace;
	@JsonProperty("status")
	private String status;
	@JsonProperty("start_time")
	private String startTime;
	@JsonProperty("Mode")
	private String mode;
	@JsonProperty("gateway")
	private String gateway;
	@JsonProperty("subnet")
	private String subnet;
	@JsonProperty("memory")
	private String memory;
	@JsonProperty("last_restarted_on_time")
	private String lastRestartedOnTime;
	@JsonProperty("ipmi")
	private String ipmi;
	@JsonProperty("key")
	private String key;
	@JsonProperty("upgrade_on_boot")
	private String upgradeOnBoot;
	

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The cloudManaged
	 */
	@JsonProperty("cloud_managed")
	public String getCloudManaged() {
		return cloudManaged;
	}

	/**
	 * 
	 * @param cloudManaged
	 *            The cloud_managed
	 */
	@JsonProperty("cloud_managed")
	public void setCloudManaged(String cloudManaged) {
		this.cloudManaged = cloudManaged;
	}
	
	/**
	 * 
	 * @return The upgradeOnBoot
	 */
	@JsonProperty("upgrade_on_boot")
	public String getUpgradeOnBoot() {
		return upgradeOnBoot;
	}

	/**
	 * 
	 * @param upgradeOnBoot
	 *            The upgrade_on_boot
	 */
	@JsonProperty("upgrade_on_boot")
	public void setUpgradeOnBoot(String upgradeOnBoot) {
		this.upgradeOnBoot = upgradeOnBoot;
	}

	/**
	 * 
	 * @return The swVersion
	 */
	@JsonProperty("sw_version")
	public String getSwVersion() {
		return swVersion;
	}

	/**
	 * 
	 * @param swVersion
	 *            The sw_version
	 */
	@JsonProperty("sw_version")
	public void setSwVersion(String swVersion) {
		this.swVersion = swVersion;
	}

	/**
	 * 
	 * @return The isLeader
	 */
	@JsonProperty("is_leader")
	public String getIsLeader() {
		return isLeader;
	}

	/**
	 * 
	 * @param isLeader
	 *            The is_leader
	 */
	@JsonProperty("is_leader")
	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}

	/**
	 * 
	 * @return The tags
	 */
	@JsonProperty("tags")
	public String getTags() {
		return tags;
	}

	/**
	 * 
	 * @param tags
	 *            The tags
	 */
	@JsonProperty("tags")
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * 
	 * @return The eulaAccepted
	 */
	@JsonProperty("eula_accepted")
	public String getEulaAccepted() {
		return eulaAccepted;
	}

	/**
	 * 
	 * @param eulaAccepted
	 *            The eula_accepted
	 */
	@JsonProperty("eula_accepted")
	public void setEulaAccepted(String eulaAccepted) {
		this.eulaAccepted = eulaAccepted;
	}

	/**
	 * 
	 * @return The cloudStatus
	 */
	@JsonProperty("cloud_status")
	public String getCloudStatus() {
		return cloudStatus;
	}

	/**
	 * 
	 * @param cloudStatus
	 *            The cloud_status
	 */
	@JsonProperty("cloud_status")
	public void setCloudStatus(String cloudStatus) {
		this.cloudStatus = cloudStatus;
	}

	/**
	 * 
	 * @return The clusterId
	 */
	@JsonProperty("clusterId")
	public String getClusterId() {
		return clusterId;
	}

	/**
	 * 
	 * @param clusterId
	 *            The clusterId
	 */
	@JsonProperty("clusterId")
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	/**
	 * 
	 * @return The serialNumber
	 */
	@JsonProperty("serial_number")
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * 
	 * @param serialNumber
	 *            The serial_number
	 */
	@JsonProperty("serial_number")
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * 
	 * @return The fwVersion
	 */
	@JsonProperty("fw_version")
	public String getFwVersion() {
		return fwVersion;
	}

	/**
	 * 
	 * @param fwVersion
	 *            The fw_version
	 */
	@JsonProperty("fw_version")
	public void setFwVersion(String fwVersion) {
		this.fwVersion = fwVersion;
	}

	/**
	 * 
	 * @return The firstBoot
	 */
	@JsonProperty("first_boot")
	public String getFirstBoot() {
		return firstBoot;
	}

	/**
	 * 
	 * @param firstBoot
	 *            The first_boot
	 */
	@JsonProperty("first_boot")
	public void setFirstBoot(String firstBoot) {
		this.firstBoot = firstBoot;
	}

	/**
	 * 
	 * @return The gpuConfiguration
	 */
	@JsonProperty("gpu_configuration")
	public String getGpuConfiguration() {
		return gpuConfiguration;
	}

	/**
	 * 
	 * @param gpuConfiguration
	 *            The gpu_configuration
	 */
	@JsonProperty("gpu_configuration")
	public void setGpuConfiguration(String gpuConfiguration) {
		this.gpuConfiguration = gpuConfiguration;
	}

	/**
	 * 
	 * @return The nodeId
	 */
	@JsonProperty("node_id")
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * 
	 * @param nodeId
	 *            The node_id
	 */
	@JsonProperty("node_id")
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * 
	 * @return The licenceKey
	 */
	@JsonProperty("licenceKey")
	public String getLicenceKey() {
		return licenceKey;
	}

	/**
	 * 
	 * @param licenceKey
	 *            The licenceKey
	 */
	@JsonProperty("licenceKey")
	public void setLicenceKey(String licenceKey) {
		this.licenceKey = licenceKey;
	}

	/**
	 * 
	 * @return The clusterGroup
	 */
	@JsonProperty("cluster_group")
	public String getClusterGroup() {
		return clusterGroup;
	}

	/**
	 * 
	 * @param clusterGroup
	 *            The cluster_group
	 */
	@JsonProperty("cluster_group")
	public void setClusterGroup(String clusterGroup) {
		this.clusterGroup = clusterGroup;
	}

	/**
	 * 
	 * @return The biosVersion
	 */
	@JsonProperty("bios_version")
	public String getBiosVersion() {
		return biosVersion;
	}

	/**
	 * 
	 * @param biosVersion
	 *            The bios_version
	 */
	@JsonProperty("bios_version")
	public void setBiosVersion(String biosVersion) {
		this.biosVersion = biosVersion;
	}

	/**
	 * 
	 * @return The cloudGroup
	 */
	@JsonProperty("cloud_group")
	public String getCloudGroup() {
		return cloudGroup;
	}

	/**
	 * 
	 * @param cloudGroup
	 *            The cloud_group
	 */
	@JsonProperty("cloud_group")
	public void setCloudGroup(String cloudGroup) {
		this.cloudGroup = cloudGroup;
	}

	/**
	 * 
	 * @return The serialid
	 */
	@JsonProperty("serialid")
	public String getSerialid() {
		return serialid;
	}

	/**
	 * 
	 * @param serialid
	 *            The serialid
	 */
	@JsonProperty("serialid")
	public void setSerialid(String serialid) {
		this.serialid = serialid;
	}

	/**
	 * 
	 * @return The ipAddress
	 */
	@JsonProperty("ip_address")
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * 
	 * @param ipAddress
	 *            The ipAddress
	 */
	@JsonProperty("ip_address")
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * 
	 * @return The modelName
	 */
	@JsonProperty("model_name")
	public String getModelName() {
		return modelName;
	}

	/**
	 * 
	 * @param modelName
	 *            The model_name
	 */
	@JsonProperty("model_name")
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}


	/**
	 * 
	 * @return The createdTime
	 */
	@JsonProperty("created_time")
	public String getCreatedTime() {
		return createdTime;
	}

	/**
	 * 
	 * @param createdTime
	 *            The created_time
	 */
	@JsonProperty("created_time")
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * 
	 * @return The diskSpace
	 */
	@JsonProperty("disk_space")
	public String getDiskSpace() {
		return diskSpace;
	}

	/**
	 * 
	 * @param diskSpace
	 *            The disk_space
	 */
	@JsonProperty("disk_space")
	public void setDiskSpace(String diskSpace) {
		this.diskSpace = diskSpace;
	}

	/**
	 * 
	 * @return The status
	 */
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 *            The status
	 */
	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return The startTime
	 */
	@JsonProperty("start_time")
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 
	 * @param startTime
	 *            The start_time
	 */
	@JsonProperty("start_time")
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 
	 * @return The mode
	 */
	@JsonProperty("Mode")
	public String getMode() {
		return mode;
	}

	/**
	 * 
	 * @param mode
	 *            The mode
	 */
	@JsonProperty("Mode")
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * 
	 * @return The memory
	 */
	@JsonProperty("memory")
	public String getMemory() {
		return memory;
	}

	/**
	 * 
	 * @param memory
	 *            The memory
	 */
	@JsonProperty("memory")
	public void setMemory(String memory) {
		this.memory = memory;
	}

	/**
	 * 
	 * @return The gateway
	 */
	@JsonProperty("gateway")
	public String getGateway() {
		return gateway;
	}

	/**
	 * 
	 * @param gateway
	 *            The gateway
	 */
	@JsonProperty("gateway")
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	
	
	/**
	 * @return the subnet
	 */
	@JsonProperty("subnet")
	public String getSubnet() {
		return subnet;
	}

	/**
	 * @param subnet the subnet to set
	 */
	@JsonProperty("subnet")
	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	/**
	 * 
	 * @return The lastRestartedOnTime
	 */
	@JsonProperty("last_restarted_on_time")
	public String getLastRestartedOnTime() {
		return lastRestartedOnTime;
	}

	/**
	 * 
	 * @param lastRestartedOnTime
	 *            The lastRestartedOnTime
	 */
	@JsonProperty("last_restarted_on_time")
	public void setLastRestartedOnTime(String lastRestartedOnTime) {
		this.lastRestartedOnTime = lastRestartedOnTime;
	}

	/**
	 * 
	 * @return The ipmi
	 */
	@JsonProperty("ipmi")
	public String getIpmi() {
		return ipmi;
	}

	/**
	 * 
	 * @param ipmi
	 *            The ipmi
	 */
	@JsonProperty("ipmi")
	public void setIpmi(String ipmi) {
		this.ipmi = ipmi;
	}

	
	/**
	 * @return the nodeName
	 */
	@JsonProperty("nodeName")
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * @param nodeName the nodeName to set
	 */
	@JsonProperty("nodeName")
	public void setHostname(String nodeName) {
		this.nodeName = nodeName;
	}
	

	/**
	 * @return the key
	 */
	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
