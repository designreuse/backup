package com.tresbu.nvidia.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.tresbu.nvidia.common.data.NvidiaData;
import com.tresbu.nvidia.common.DateUtil;

/**
 * The persistent class for the node database table.
 * 
 */
public class NodeEntity extends NvidiaData implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String key;

	private String serialId;

	private String nodeId;

	private String biosVersion;

	private String clusterId;

	private Timestamp createdTime;

	private String diskSpace;

	private String ipAddress;

	private Timestamp lastRestartedOnTime;

	private String memory;

	private String tags;

	private String status;

	private String licenceKey;

	private String mode;

	private String swVersion;

	private String fwVersion;

	private String ipmi;

	private Timestamp startTime;

	private String isLeader;

	private String subnet;

	private String clusterGroup;

	private String nodenetworkinformation;

	private String gateway;

	private String nodeName;

	private String cloudManaged;
	private String firstBoot;
	private String eulaAccepted;
	private String serialNumber;
	private String cloudStatus;
	private String gpuConfiguration;
	private String cloudGroup;
	private String modelName;

	// bi-directional many-to-one association to Alert
	private List<AlertEntity> alerts;

	// bi-directional many-to-one association to AlertSummary
	private List<AlertSummaryEntity> alertSummaries;

	// bi-directional many-to-one association to Job
	private List<JobEntity> jobs;

	// bi-directional one-to-one association to Cluster
	private ClusterEntity cluster;

	// bi-directional one-to-one association to Status

	public NodeEntity() {
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getSerialId() {
		return this.serialId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getBiosVersion() {
		return this.biosVersion;
	}

	public void setBiosVersion(String biosVersion) {
		this.biosVersion = biosVersion;
	}

	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

//	public void setCreatedTime(String pCreatedTime) {
//		this.createdTime = DateUtil.convertStringToTimeStamp(pCreatedTime);
//	}

	public String getDiskSpace() {
		return this.diskSpace;
	}

	public void setDiskSpace(String diskSpace) {
		this.diskSpace = diskSpace;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Timestamp getLastRestartedOnTime() {
		return this.lastRestartedOnTime;
	}

//	public void setLastRestartedOnTime(String pLastRestartedOnTime) {
//		this.lastRestartedOnTime = DateUtil.convertStringToTimeStamp(pLastRestartedOnTime);
//	}

	public String getMemory() {
		return this.memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<AlertEntity> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<AlertEntity> alerts) {
		this.alerts = alerts;
	}

	public AlertEntity addAlert(AlertEntity alert) {
		getAlerts().add(alert);
		alert.setNode(this);

		return alert;
	}

	public AlertEntity removeAlert(AlertEntity alert) {
		getAlerts().remove(alert);
		alert.setNode(null);

		return alert;
	}

	public List<AlertSummaryEntity> getAlertSummaries() {
		return this.alertSummaries;
	}

	public void setAlertSummaries(List<AlertSummaryEntity> alertSummaries) {
		this.alertSummaries = alertSummaries;
	}

	public AlertSummaryEntity addAlertSummary(AlertSummaryEntity alertSummary) {
		getAlertSummaries().add(alertSummary);
		alertSummary.setNode(this);

		return alertSummary;
	}

	public AlertSummaryEntity removeAlertSummary(AlertSummaryEntity alertSummary) {
		getAlertSummaries().remove(alertSummary);
		alertSummary.setNode(null);

		return alertSummary;
	}

	public List<JobEntity> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<JobEntity> jobs) {
		this.jobs = jobs;
	}

	public JobEntity addJob(JobEntity job) {
		getJobs().add(job);
		job.setNode(this);

		return job;
	}

	public JobEntity removeJob(JobEntity job) {
		getJobs().remove(job);
		job.setNode(null);

		return job;
	}

	public ClusterEntity getCluster() {
		return this.cluster;
	}

	public void setCluster(ClusterEntity cluster) {
		this.cluster = cluster;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLicenceKey() {
		return licenceKey;
	}

	public void setLicenceKey(String licenceKey) {
		this.licenceKey = licenceKey;
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

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = DateUtil.convertStringToTimeStamp(startTime);
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

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public void setLastRestartedOnTime(Timestamp lastRestartedOnTime) {
		this.lastRestartedOnTime = lastRestartedOnTime;
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

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
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

	@Override
	public String toString() {
		return "NodeEntity [id=" + id + ", key=" + key + ", serialId=" + serialId + ", nodeId=" + nodeId + ", biosVersion=" + biosVersion
				+ ", clusterId=" + clusterId + ", createdTime=" + createdTime + ", diskSpace=" + diskSpace + ", ipAddress=" + ipAddress
				+ ", lastRestartedOnTime=" + lastRestartedOnTime + ", memory=" + memory + ", tags=" + tags + ", status=" + status + ", licenceKey="
				+ licenceKey + ", mode=" + mode + ", swVersion=" + swVersion + ", fwVersion=" + fwVersion + ", ipmi=" + ipmi + ", startTime="
				+ startTime + ", isLeader=" + isLeader + ", subnet=" + subnet + ", clusterGroup=" + clusterGroup + ", nodenetworkinformation="
				+ nodenetworkinformation + ", gateway=" + gateway + ", nodeName=" + nodeName + ", cloudManaged=" + cloudManaged + ", firstBoot="
				+ firstBoot + ", eulaAccepted=" + eulaAccepted + ", serialNumber=" + serialNumber + ", cloudStatus=" + cloudStatus
				+ ", gpuConfiguration=" + gpuConfiguration + ", cloudGroup=" + cloudGroup + ", modelName=" + modelName + ", alerts=" + alerts
				+ ", alertSummaries=" + alertSummaries + ", jobs=" + jobs + ", cluster=" + cluster + "]";
	}

}