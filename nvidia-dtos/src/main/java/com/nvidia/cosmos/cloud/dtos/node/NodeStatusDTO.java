
package com.nvidia.cosmos.cloud.dtos.node;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cloud_managed", "sw_version", "is_leader", "tags", "eula_accepted", "cloud_status", "cluster_id", "serial_number", "fw_version",
		"first_boot", "gpu_configuration", "node_id", "licenceKey", "cluster_group", "bios_version", "cloud_group", "serialid", "ip_address",
		"model_name", "node_name", "created_time", "disk_space", "status", "start_time", "Mode", "gateway", "subnet", "memory",
		"last_restarted_on_time", "bmc_version", "key", "cloud_url", "user_added", "total_cpu_cores", "repo_url", "upgrade_on_boot" })
public class NodeStatusDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("cloud_managed")
	private String cloudManaged;
	@JsonProperty("sw_version")
	private String swVersion;
	
	@NotNull(message = "Is Leader must not be Null!")
	@NotBlank(message = "Is Leader must not be blank!")
	@NotEmpty(message = "Is Leader must not be Empty!")
	@JsonProperty("is_leader")
	@ApiModelProperty(required = true, value="Mandatory")
	private String isLeader;
	
	
	@JsonProperty("tags")
	private String tags;
	@JsonProperty("eula_accepted")
	private String eulaAccepted;
	
	@NotNull(message = "Cloud Status must not be Null!")
	@NotBlank(message = "Cloud Status must not be blank!")
	@NotEmpty(message = "Cloud Status must not be Empty!")
	@JsonProperty("cloud_status")
	@ApiModelProperty(required = true, value="Mandatory")
	private String cloudStatus;
	
	@NotNull(message = "Cluster Name must not be Null!")
	@NotBlank(message = "Cluster Name must not be blank!")
	@NotEmpty(message = "Cluster Name must not be Empty!")
	@JsonProperty("cluster_id")
	@ApiModelProperty(required = true, value="Mandatory")
	private String clusterId;
	
	@NotNull(message = "Serial Number must not be Null!")
	@NotBlank(message = "Serial Number must not be blank!")
	@NotEmpty(message = "Serial Number must not be Empty!")
	@JsonProperty("serial_number")
	@ApiModelProperty(required = true, value="Mandatory")
	private String serialNumber;

	@JsonProperty("fw_version")
	private String fwVersion;

	@JsonProperty("first_boot")
	private String firstBoot;
	
	@NotNull(message = "Gpu Configuration must not be Null!")
	@NotBlank(message = "Gpu Configuration must not be blank!")
	@NotEmpty(message = "Gpu Configuration must not be Empty!")
	@Pattern(regexp = "^[0-9]+$",message="Gpu Configuration value must be number!")
	@JsonProperty("gpu_configuration")
	@ApiModelProperty(required = true, value="Mandatory")
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
	
	@NotNull(message = "Ip Address must not be Null!")
	@NotBlank(message = "Ip Address must not be blank!")
	@NotEmpty(message = "Ip Address must not be Empty!")
	@Pattern(regexp="^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$",message="Ip Address must be valid!")
	@JsonProperty("ip_address")
	@ApiModelProperty(required = true, value="Mandatory")
	private String ipAddress;
	@JsonProperty("model_name")
	private String modelName;
	
	@NotNull(message = "Node name must not be Null!")
	@NotBlank(message = "Node name must not be blank!")
	@NotEmpty(message = "Node name must not be Empty!")
	@JsonProperty("node_name")
	@ApiModelProperty(required = true, value="Mandatory")
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
	
	@NotNull(message = "Memory must not be Null!")
	@NotBlank(message = "Memory must not be blank!")
	@NotEmpty(message = "Memory must not be Empty!")
	@Pattern(regexp = "[0-9]+",message="Memory value must be number!")
	@JsonProperty("memory")
	@ApiModelProperty(required = true, value="Mandatory")
	private String memory;
	
	@JsonProperty("bmc_version")
	private String ipmi;
	@JsonProperty("key")
	private String key;

	@JsonProperty("cloud_url")
	private String cloudUrl;
	@JsonProperty("user_added")
	private String userAdded;
	
	@NotNull(message = "Total Cpu Cores must not be Null!")
	@NotBlank(message = "Total Cpu Cores must not be blank!")
	@NotEmpty(message = "Total Cpu Cores must not be Empty!")
	@Pattern(regexp = "[0-9]+",message="Total Cpu Cores value must be number!")
	@JsonProperty("total_cpu_cores")
	@ApiModelProperty(required = true, value="Mandatory")
	private String totalCpuCores;
	
	@JsonProperty("repo_url")
	private String repoUrl;

	@JsonProperty("upgrade_on_boot")
	private String upgradeOnBoot;

	@JsonProperty("time_of_reboot")
	private String timeOfReboot;
	
	@NotNull(message = "Healthy must not be Null!")
	@NotBlank(message = "Healthy must not be blank!")
	@NotEmpty(message = "Healthy must not be Empty!")
	@Pattern(regexp = "[0-1]+",message="Healthy value must be 0 or 1!")
	@JsonProperty("healthy")
	@ApiModelProperty(required = true, value="Mandatory")
	private String healthy;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public NodeStatusDTO() {
	}

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
	 * @return The upgradeOnBoot
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
	@JsonProperty("cluster_id")
	public String getClusterId() {
		return clusterId;
	}

	/**
	 * 
	 * @param clusterId
	 *            The clusterId
	 */
	@JsonProperty("cluster_id")
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
	 * @param subnet
	 *            the subnet to set
	 */
	@JsonProperty("subnet")
	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	/**
	 * 
	 * @return The ipmi
	 */
	@JsonProperty("bmc_version")
	public String getIpmi() {
		return ipmi;
	}

	/**
	 * 
	 * @param ipmi
	 *            The ipmi
	 */
	@JsonProperty("bmc_version")
	public void setIpmi(String ipmi) {
		this.ipmi = ipmi;
	}

	/**
	 * @return the nodeName
	 */
	@JsonProperty("node_name")
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * @param nodeName
	 *            the nodeName to set
	 */
	@JsonProperty("node_name")
	public void setNodeName(String nodeName) {
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
	 * @param key
	 *            the key to set
	 */
	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	@JsonProperty("cloud_url")
	public String getCloudUrl() {
		return cloudUrl;
	}

	@JsonProperty("cloud_url")
	public void setCloudUrl(String cloudUrl) {
		this.cloudUrl = cloudUrl;
	}

	@JsonProperty("user_added")
	public String getUserAdded() {
		return userAdded;
	}

	@JsonProperty("user_added")
	public void setUserAdded(String userAdded) {
		this.userAdded = userAdded;
	}

	@JsonProperty("total_cpu_cores")
	public String getTotalCpuCores() {
		return totalCpuCores;
	}

	@JsonProperty("total_cpu_cores")
	public void setTotalCpuCores(String totalCpuCores) {
		this.totalCpuCores = totalCpuCores;
	}

	@JsonProperty("repo_url")
	public String getRepoUrl() {
		return repoUrl;
	}

	@JsonProperty("repo_url")
	public void setRepoUrl(String repoUrl) {
		this.repoUrl = repoUrl;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@JsonProperty("healthy")
	public String getHealthy() {
		return healthy;
	}
	
	@JsonProperty("healthy")
	public void setHealthy(String healthy) {
		this.healthy = healthy;
	}

	/**
	 * @return the timeOfReboot
	 */
	public String getTimeOfReboot() {
		return timeOfReboot;
	}

	/**
	 * @param timeOfReboot
	 *            the timeOfReboot to set
	 */
	public void setTimeOfReboot(String timeOfReboot) {
		this.timeOfReboot = timeOfReboot;
	}

	/**
	 * @param cloudManaged
	 * @param swVersion
	 * @param isLeader
	 * @param tags
	 * @param eulaAccepted
	 * @param cloudStatus
	 * @param clusterId
	 * @param serialNumber
	 * @param fwVersion
	 * @param firstBoot
	 * @param gpuConfiguration
	 * @param nodeId
	 * @param licenceKey
	 * @param clusterGroup
	 * @param biosVersion
	 * @param cloudGroup
	 * @param serialid
	 * @param ipAddress
	 * @param modelName
	 * @param nodeName
	 * @param createdTime
	 * @param diskSpace
	 * @param status
	 * @param startTime
	 * @param mode
	 * @param gateway
	 * @param subnet
	 * @param memory
	 * @param ipmi
	 * @param key
	 * @param cloudUrl
	 * @param userAdded
	 * @param totalCpuCores
	 * @param repoUrl
	 * @param upgradeOnBoot
	 * @param timeOfReboot
	 * @param additionalProperties
	 */
	public NodeStatusDTO(String cloudManaged, String swVersion, String isLeader, String tags, String eulaAccepted, String cloudStatus,
			String clusterId, String serialNumber, String fwVersion, String firstBoot, String gpuConfiguration, String nodeId, String licenceKey,
			String clusterGroup, String biosVersion, String cloudGroup, String serialid, String ipAddress, String modelName, String nodeName,
			String createdTime, String diskSpace, String status, String startTime, String mode, String gateway, String subnet, String memory,
			String ipmi, String key, String cloudUrl, String userAdded, String totalCpuCores, String repoUrl, String upgradeOnBoot,
			String timeOfReboot, Map<String, Object> additionalProperties) {
		super();
		this.cloudManaged = cloudManaged;
		this.swVersion = swVersion;
		this.isLeader = isLeader;
		this.tags = tags;
		this.eulaAccepted = eulaAccepted;
		this.cloudStatus = cloudStatus;
		this.clusterId = clusterId;
		this.serialNumber = serialNumber;
		this.fwVersion = fwVersion;
		this.firstBoot = firstBoot;
		this.gpuConfiguration = gpuConfiguration;
		this.nodeId = nodeId;
		this.licenceKey = licenceKey;
		this.clusterGroup = clusterGroup;
		this.biosVersion = biosVersion;
		this.cloudGroup = cloudGroup;
		this.serialid = serialid;
		this.ipAddress = ipAddress;
		this.modelName = modelName;
		this.nodeName = nodeName;
		this.createdTime = createdTime;
		this.diskSpace = diskSpace;
		this.status = status;
		this.startTime = startTime;
		this.mode = mode;
		this.gateway = gateway;
		this.subnet = subnet;
		this.memory = memory;
		this.ipmi = ipmi;
		this.key = key;
		this.cloudUrl = cloudUrl;
		this.userAdded = userAdded;
		this.totalCpuCores = totalCpuCores;
		this.repoUrl = repoUrl;
		this.upgradeOnBoot = upgradeOnBoot;
		this.timeOfReboot = timeOfReboot;
		this.additionalProperties = additionalProperties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NodeStatusDTO [cloudManaged=" + cloudManaged + ", swVersion=" + swVersion + ", isLeader=" + isLeader + ", tags=" + tags
				+ ", eulaAccepted=" + eulaAccepted + ", cloudStatus=" + cloudStatus + ", clusterId=" + clusterId + ", serialNumber=" + serialNumber
				+ ", fwVersion=" + fwVersion + ", firstBoot=" + firstBoot + ", gpuConfiguration=" + gpuConfiguration + ", nodeId=" + nodeId
				+ ", licenceKey=" + licenceKey + ", clusterGroup=" + clusterGroup + ", biosVersion=" + biosVersion + ", cloudGroup=" + cloudGroup
				+ ", serialid=" + serialid + ", ipAddress=" + ipAddress + ", modelName=" + modelName + ", nodeName=" + nodeName + ", createdTime="
				+ createdTime + ", diskSpace=" + diskSpace + ", status=" + status + ", startTime=" + startTime + ", mode=" + mode + ", gateway="
				+ gateway + ", subnet=" + subnet + ", memory=" + memory + ", ipmi=" + ipmi + ", key=" + key + ", cloudUrl=" + cloudUrl
				+ ", userAdded=" + userAdded + ", totalCpuCores=" + totalCpuCores + ", repoUrl=" + repoUrl + ", upgradeOnBoot=" + upgradeOnBoot
				+ ", timeOfReboot=" + timeOfReboot + ", additionalProperties=" + additionalProperties + "]";
	}

}
