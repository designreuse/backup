package com.nvidia.cosmos.cloud.services.node.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.model.AbstractModel;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.job.model.Job;

/**
 * @author pbatta
 *
 */
@Entity
@Table(name = ServicesConstants.NODE_TABLE_NAME)
public class Node extends AbstractModel {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Size(min = 3, max = 1000, message = "Name should be minimum 3 and maxiumum 1000")
	@Column(name = "NAME", nullable = false)
	private String name;

	/**
	 * 
	 */
	@NotEmpty(message = "Serial number should not be empty")
	@Size(min = 3, max = 1000, message = "Serial number should be minimum 3 and maxiumum 1000")
	@Column(name = "SERIAL_NUMBER", nullable = false, unique = true)
	private String serialNumber;

	/**
	 * 
	 */
	@Column(name = "NODE_KEY", nullable = true)
	private String nodeKey;

	/**
	 * 
	 */
	@Column(name = "CLOUD_MANAGED", nullable = true)
	private String cloudManaged;

	/**
	 * 
	 */
	@Column(name = "IS_LEADER", nullable = false)
	private String isleader;

	/**
	 * 
	 */
	@Column(name = "FW_VERSION", nullable = true)
	private String fwVersion;

	/**
	 * 
	 */
	@Column(name = "SERIAL_ID", nullable = true)
	private String serialId;

	/**
	 * 
	 */
	@Column(name = "SUB_NET", nullable = true)
	private String subNet;

	/**
	 * 
	 */
	@Column(name = "FIRST_BOOT", nullable = true)
	private String firstBoot;

	/**
	 * 
	 */
	@Column(name = "EULA_ACCEPTED", nullable = true)
	private String eulaAccepted;

	/**
	 * 
	 */
	@Column(name = "BIOS_VERSION", nullable = true)
	private String biosVersion;

	/**
	 * 
	 */
	@Column(name = "CLOUD_STATUS", nullable = true)
	private String cloudStatus;

	/**
	 * 
	 */
	@Column(name = "TAGS", nullable = true)
	private String tags;

	/**
	 * 
	 */
	@Column(name = "GPU_CONFIGURATION", nullable = false)
	private String gpuConfiguration;

	/**
	 * 
	 */
	@Column(name = "IPMI", nullable = true)
	private String ipmi;

	/**
	 * 
	 */
	@Column(name = "NODE_ID", nullable = true)
	private String nodeId;

	/**
	 * 
	 */
	@Column(name = "MODE", nullable = true)
	private String mode;

	/**
	 * 
	 */
	@Column(name = "IP_ADDRESS", nullable = false)
	private String ipAddress;

	/**
	 * 
	 */
	@Column(name = "SW_VERSION", nullable = true)
	private String swVersion;

	/**
	 * 
	 */
	@Column(name = "CLOUD_GROUP", nullable = true)
	private String cloudGroup;

	/**
	 * 
	 */
	@Column(name = "CLUSTER_GROUP", nullable = true)
	private String clusterGroup;

	/**
	 * 
	 */
	@Column(name = "MODEL_NAME", nullable = true)
	private String modelName;

	/**
	 * 
	 */
	@Column(name = "MEMORY", nullable = false)
	private String memory;

	/**
	 * 
	 */
	@Column(name = "DISK_SPACE")
	private String diskSpace;

	/**
	 * 
	 */
	@Column(name = "GATEWAY")
	private String gateway;

	/**
	 * 
	 */
	@Column(name = "CREATED_DATE", nullable = false)
	private Date createdDate;

	/**
	 * 
	 */
	@Column(name = "UPDATED_DATE", nullable = true)
	private Date updatedDate;

	/**
	 * 
	 */
	@Column(name = "CLOUD_URL", nullable = true)
	private String cloudUrl;

	/**
	 * 
	 */
	@Column(name = "USER_ADDED", nullable = true)
	private String userAdded;

	/**
	 * 
	 */
	@Column(name = "TOTAL_CPU_CORES", nullable = false)
	private String totalCpuCores;

	/**
	 * 
	 */
	@Column(name = "REPO_URL", nullable = true)
	private String repoUrl;

	/**
	 * 
	 */
	@Column(name = "UPGRADE_ON_BOOT", nullable = true)
	private String upgradeOnBoot;

	/**
	 * 
	 */
	@Column(name = "TIME_OF_REBOOT", nullable = true)
	private String timeOfReboot;

	@Column(name = "HEALTHY", nullable = true)
	private String healthy;
	/**
	 * 
	 */
	@ManyToOne(targetEntity = Cluster.class)
	@JoinColumn(name = "CLUSTER_ID")
	private Cluster cluster;

	@OneToMany
	@Cascade(CascadeType.DELETE)
	@JoinColumn(name = "NODE_ID")
	private Set<Job> jobs;

	/**
	 * 
	 */
	public Node() {

	}

	/**
	 * @param name
	 * @param serialNumber
	 * @param nodeKey
	 * @param cloudManaged
	 * @param isleader
	 * @param fwVersion
	 * @param licenceKey
	 * @param serialId
	 * @param subNet
	 * @param firstBoot
	 * @param eulaAccepted
	 * @param biosVersion
	 * @param cloud_status
	 * @param tags
	 * @param gpuConfiguration
	 * @param ipmi
	 * @param nodeId
	 * @param mode
	 * @param ipAddress
	 * @param sw_version
	 * @param cloudGroup
	 * @param clusterGroup
	 * @param modelName
	 * @param memory
	 * @param diskSpace
	 * @param gateway
	 * @param cloudUrl
	 * @param userAdded
	 * @param totalCpuCores
	 * @param repoUrl
	 * @param cluster
	 * @param upgradeOnBoot
	 */
	public Node(String name, String serialNumber, String nodeKey, String cloudManaged, String isleader,
			String fwVersion, String licenceKey, String serialId, String subNet, String firstBoot, String eulaAccepted,
			String biosVersion, String cloudStatus, String tags, String gpuConfiguration, String ipmi, String nodeId,
			String mode, String ipAddress, String swVersion, String cloudGroup, String clusterGroup, String modelName,
			String memory, String diskSpace, String gateway, String cloudUrl, String userAdded, String totalCpuCores,
			String repoUrl, Cluster cluster, String upgradeOnBoot, String healthy) {
		super();
		this.name = name;
		this.serialNumber = serialNumber;
		this.nodeKey = nodeKey;
		this.cloudManaged = cloudManaged;
		this.isleader = isleader;
		this.fwVersion = fwVersion;
		this.serialId = serialId;
		this.subNet = subNet;
		this.firstBoot = firstBoot;
		this.eulaAccepted = eulaAccepted;
		this.biosVersion = biosVersion;
		this.cloudStatus = cloudStatus;
		this.tags = tags;
		this.gpuConfiguration = gpuConfiguration;
		this.ipmi = ipmi;
		this.nodeId = nodeId;
		this.mode = mode;
		this.ipAddress = ipAddress;
		this.swVersion = swVersion;
		this.cloudGroup = cloudGroup;
		this.clusterGroup = clusterGroup;
		this.modelName = modelName;
		this.memory = memory;
		this.diskSpace = diskSpace;
		this.gateway = gateway;
		this.cloudUrl = cloudUrl;
		this.userAdded = userAdded;
		this.totalCpuCores = totalCpuCores;
		this.repoUrl = repoUrl;
		this.cluster = cluster;
		this.upgradeOnBoot = upgradeOnBoot;
		this.createdDate = new Date();
		this.updatedDate = new Date();
		this.healthy = healthy;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the upgradeOnBoot
	 */
	public String getUpgradeOnBoot() {
		return upgradeOnBoot;
	}

	/**
	 * @param upgradeOnBoot
	 *            the upgradeOnBoot to set
	 */
	public void setUpgradeOnBoot(String upgradeOnBoot) {
		this.upgradeOnBoot = upgradeOnBoot;
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

	/**
	 * @return the nodeKey
	 */
	public String getNodeKey() {
		return nodeKey;
	}

	/**
	 * @param nodeKey
	 *            the nodeKey to set
	 */
	public void setNodeKey(String nodeKey) {
		this.nodeKey = nodeKey;
	}

	/**
	 * @return the cloudManaged
	 */
	public String getCloudManaged() {
		return cloudManaged;
	}

	/**
	 * @param cloudManaged
	 *            the cloudManaged to set
	 */
	public void setCloudManaged(String cloudManaged) {
		this.cloudManaged = cloudManaged;
	}

	/**
	 * @return the isleader
	 */
	public String getIsleader() {
		return isleader;
	}

	/**
	 * @param isleader
	 *            the isleader to set
	 */
	public void setIsleader(String isleader) {
		this.isleader = isleader;
	}

	/**
	 * @return the fwVersion
	 */
	public String getFwVersion() {
		return fwVersion;
	}

	/**
	 * @param fwVersion
	 *            the fwVersion to set
	 */
	public void setFwVersion(String fwVersion) {
		this.fwVersion = fwVersion;
	}

	/**
	 * @return the serialId
	 */
	public String getSerialId() {
		return serialId;
	}

	/**
	 * @param serialId
	 *            the serialId to set
	 */
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	/**
	 * @return the subNet
	 */
	public String getSubNet() {
		return subNet;
	}

	/**
	 * @param subNet
	 *            the subNet to set
	 */
	public void setSubNet(String subNet) {
		this.subNet = subNet;
	}

	/**
	 * @return the firstBoot
	 */
	public String getFirstBoot() {
		return firstBoot;
	}

	/**
	 * @param firstBoot
	 *            the firstBoot to set
	 */
	public void setFirstBoot(String firstBoot) {
		this.firstBoot = firstBoot;
	}

	/**
	 * @return the eulaAccepted
	 */
	public String getEulaAccepted() {
		return eulaAccepted;
	}

	/**
	 * @param eulaAccepted
	 *            the eulaAccepted to set
	 */
	public void setEulaAccepted(String eulaAccepted) {
		this.eulaAccepted = eulaAccepted;
	}

	/**
	 * @return the biosVersion
	 */
	public String getBiosVersion() {
		return biosVersion;
	}

	/**
	 * @param biosVersion
	 *            the biosVersion to set
	 */
	public void setBiosVersion(String biosVersion) {
		this.biosVersion = biosVersion;
	}

	/**
	 * @return the cloudStatus
	 */
	public String getCloudStatus() {
		return cloudStatus;
	}

	/**
	 * @param cloudStatus
	 *            the cloudStatus to set
	 */
	public void setCloudStatus(String cloudStatus) {
		this.cloudStatus = cloudStatus;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the gpuConfiguration
	 */
	public String getGpuConfiguration() {
		return gpuConfiguration;
	}

	/**
	 * @param gpuConfiguration
	 *            the gpuConfiguration to set
	 */
	public void setGpuConfiguration(String gpuConfiguration) {
		this.gpuConfiguration = gpuConfiguration;
	}

	/**
	 * @return the ipmi
	 */
	public String getIpmi() {
		return ipmi;
	}

	/**
	 * @param ipmi
	 *            the ipmi to set
	 */
	public void setIpmi(String ipmi) {
		this.ipmi = ipmi;
	}

	/**
	 * @return the nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId
	 *            the nodeId to set
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the swVersion
	 */
	public String getSwVersion() {
		return swVersion;
	}

	/**
	 * @param swVersion
	 *            the swVersion to set
	 */
	public void setSwVersion(String swVersion) {
		this.swVersion = swVersion;
	}

	/**
	 * @return the cloudGroup
	 */
	public String getCloudGroup() {
		return cloudGroup;
	}

	/**
	 * @param cloudGroup
	 *            the cloudGroup to set
	 */
	public void setCloudGroup(String cloudGroup) {
		this.cloudGroup = cloudGroup;
	}

	/**
	 * @return the clusterGroup
	 */
	public String getClusterGroup() {
		return clusterGroup;
	}

	/**
	 * @param clusterGroup
	 *            the clusterGroup to set
	 */
	public void setClusterGroup(String clusterGroup) {
		this.clusterGroup = clusterGroup;
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName
	 *            the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the memory
	 */
	public String getMemory() {
		return memory;
	}

	/**
	 * @param memory
	 *            the memory to set
	 */
	public void setMemory(String memory) {
		this.memory = memory;
	}

	/**
	 * @return the diskSpace
	 */
	public String getDiskSpace() {
		return diskSpace;
	}

	/**
	 * @param diskSpace
	 *            the diskSpace to set
	 */
	public void setDiskSpace(String diskSpace) {
		this.diskSpace = diskSpace;
	}

	/**
	 * @return the gateway
	 */
	public String getGateway() {
		return gateway;
	}

	/**
	 * @param gateway
	 *            the gateway to set
	 */
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the cloudUrl
	 */

	public String getCloudUrl() {
		return cloudUrl;
	}

	/**
	 * @param cloudUrl
	 *            the cloudUrl to set
	 */

	public void setCloudUrl(String cloudUrl) {
		this.cloudUrl = cloudUrl;
	}

	/**
	 * @return the userAdded
	 */

	public String getUserAdded() {
		return userAdded;
	}

	/**
	 * @param userAdded
	 *            the userAdded to set
	 */

	public void setUserAdded(String userAdded) {
		this.userAdded = userAdded;
	}

	/**
	 * @return the totalCpuCores
	 */

	public String getTotalCpuCores() {
		return totalCpuCores;
	}

	/**
	 * @param totalCpuCores
	 *            the totalCpuCores to set
	 */

	public void setTotalCpuCores(String totalCpuCores) {
		this.totalCpuCores = totalCpuCores;
	}

	/**
	 * @return the repoUrl
	 */

	public String getRepoUrl() {
		return repoUrl;
	}

	/**
	 * @param repoUrl
	 *            the repoUrl to set
	 */

	public void setRepoUrl(String repoUrl) {
		this.repoUrl = repoUrl;
	}

	/**
	 * @return the cluster
	 */
	public Cluster getCluster() {
		return cluster;
	}

	/**
	 * @param cluster
	 *            the cluster to set
	 */
	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
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
	 * @return the jobs
	 */
	public Set<Job> getJobs() {
		return jobs;
	}

	/**
	 * @param jobs
	 *            the jobs to set
	 */
	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	/**
	 * @return the healthy
	 */
	public String getHealthy() {
		return healthy;
	}

	/**
	 * @param healthy
	 *            the healthy to set
	 */
	public void setHealthy(String healthy) {
		this.healthy = healthy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [id=" + id + ", name=" + name + ", serialNumber=" + serialNumber + ", nodeKey=" + nodeKey
				+ ", cloudManaged=" + cloudManaged + ", isleader=" + isleader + ", fwVersion=" + fwVersion
				+ ", serialId=" + serialId + ", subNet=" + subNet + ", firstBoot=" + firstBoot + ", eulaAccepted="
				+ eulaAccepted + ", biosVersion=" + biosVersion + ", cloudStatus=" + cloudStatus + ", tags=" + tags
				+ ", gpuConfiguration=" + gpuConfiguration + ", ipmi=" + ipmi + ", nodeId=" + nodeId + ", mode=" + mode
				+ ", ipAddress=" + ipAddress + ", swVersion=" + swVersion + ", cloudGroup=" + cloudGroup
				+ ", clusterGroup=" + clusterGroup + ", modelName=" + modelName + ", memory=" + memory + ", diskSpace="
				+ diskSpace + ", gateway=" + gateway + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", cloudUrl=" + cloudUrl + ", userAdded=" + userAdded + ", totalCpuCores=" + totalCpuCores
				+ ", repoUrl=" + repoUrl + ", upgradeOnBoot=" + upgradeOnBoot + ", timeOfReboot=" + timeOfReboot
				+ ", cluster=" + cluster + ", jobs=" + jobs + "]";
	}

}
