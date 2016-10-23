package com.tresbu.nvidia.utility;

import java.util.ArrayList;
import java.util.List;

import com.tresbu.nvidia.common.data.AlertData;
import com.tresbu.nvidia.common.data.AlertSummaryData;
import com.tresbu.nvidia.common.data.ClusterData;
import com.tresbu.nvidia.common.data.JobData;
import com.tresbu.nvidia.common.data.JobTaskData;
import com.tresbu.nvidia.common.data.NodeInfoData;
import com.tresbu.nvidia.json.pojo.Acknowledged;
import com.tresbu.nvidia.json.pojo.Child;
import com.tresbu.nvidia.json.pojo.DockerImages;
import com.tresbu.nvidia.json.pojo.NeedAck;
import com.tresbu.nvidia.json.pojo.job.Job;
import com.tresbu.nvidia.json.pojo.job.JobDetails;
import com.tresbu.nvidia.json.pojo.job.Task;

public class DataListWrapper {

	private List<NodeInfoData> nodeInfoDataList;
	private List<AlertSummaryData> alertSummaryDataList;
	private List<JobData> jobDatasList;
	private List<AlertData> alertDataList;
	private List<ClusterData> clusterDataList;
	private ClusterData clusterData;
	private DockerImages dockerImages;
	private List<NeedAck> needAckList;
	private List<Child> childList;
	private List<Acknowledged> acknowledgeList;
	private Long criticalCount = 0l;
	private Long ackCount = 0l;
	private Long needAckCount = 0l;
	private Long unknownCount = 0l;
	private Long warningCount = 0l;

	private Job job;
	private List<Task> taskList;
	private List<JobDetails> jobDetailsList;
	private List<JobTaskData> jobTaskDataList;
	private String containerResponse;

	private String serverIpAddress;
	private String nvidiaHost;
	private String bosunHost;
	private String grafanaHost;

	public DataListWrapper() {
		nodeInfoDataList = new ArrayList<NodeInfoData>();
		alertSummaryDataList = new ArrayList<AlertSummaryData>();
		jobDatasList = new ArrayList<JobData>();
		alertDataList = new ArrayList<AlertData>();
		clusterDataList = new ArrayList<ClusterData>();
		clusterData = new ClusterData();
		needAckList = new ArrayList<NeedAck>();
		childList = new ArrayList<Child>();
		taskList = new ArrayList<Task>();
		acknowledgeList = new ArrayList<Acknowledged>();
		jobDetailsList = new ArrayList<JobDetails>();
		jobTaskDataList = new ArrayList<JobTaskData>();
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public void addTask(Task pTask) {
		this.taskList.add(pTask);
	}

	/**
	 * @return the job
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * @param job
	 *            the job to set
	 */
	public void setJob(Job job) {
		this.job = job;
	}

	public List<NodeInfoData> getNodeInfoDataList() {
		return nodeInfoDataList;
	}

	public void setNodeInfoDataList(List<NodeInfoData> nodeInfoDataList) {
		this.nodeInfoDataList = nodeInfoDataList;
	}

	public List<AlertSummaryData> getAlertSummaryDataList() {
		return alertSummaryDataList;
	}

	public void setAlertSummaryDataList(List<AlertSummaryData> alertSummaryDataList) {
		this.alertSummaryDataList = alertSummaryDataList;
	}

	public List<JobData> getJobDatasList() {
		return jobDatasList;
	}

	public void setJobDatasList(List<JobData> jobDatasList) {
		this.jobDatasList = jobDatasList;
	}

	public List<AlertData> getAlertDataList() {
		return alertDataList;
	}

	public void setAlertDataList(List<AlertData> alertDataList) {
		this.alertDataList = alertDataList;
	}

	public void addClusterData(ClusterData pClusterData) {
		clusterDataList.add(pClusterData);
	}

	public void addNodeInfoData(NodeInfoData pNodeInfoData) {
		nodeInfoDataList.add(pNodeInfoData);
	}

	public List<ClusterData> getClusterDataList() {
		return clusterDataList;
	}

	public void setClusterDataList(List<ClusterData> clusterDataList) {
		this.clusterDataList = clusterDataList;
	}

	public void addAllClusterDataList(List<ClusterData> clusterDataList) {
		clusterDataList.addAll(clusterDataList);
	}

	public void addAllNodeDataList(List<NodeInfoData> nodeInfoDatas) {
		nodeInfoDataList.addAll(nodeInfoDatas);
	}

	public void addAllJobDataList(List<JobData> pJobDataList) {
		jobDatasList.addAll(pJobDataList);
	}

	public ClusterData getClusterData() {
		return clusterData;
	}

	public void setClusterData(ClusterData clusterData) {
		this.clusterData = clusterData;
	}

	public List<NeedAck> getNeedAckList() {
		return needAckList;
	}

	public void setNeedAckList(List<NeedAck> needAckList) {
		this.needAckList = needAckList;
	}

	public void addAllNeedAckList(List<NeedAck> needAckList) {
		this.needAckList.addAll(needAckList);
	}

	
	public List<Child> getChildList() {
		return childList;
	}

	public void setChildList(List<Child> childList) {
		this.childList = childList;
	}
	
	public void addAllChildList(List<Child> childList) {
		this.childList.addAll(childList);
	}

	public void addChild(Child pChild) {
		this.childList.add(pChild);
	}

	/**
	 * @return the jobTaskDataList
	 */
	public List<JobTaskData> getJobTaskDataList() {
		return jobTaskDataList;
	}

	/**
	 * @param jobTaskDataList
	 *            the jobTaskDataList to set
	 */
	public void setJobTaskDataList(List<JobTaskData> jobTaskDataList) {
		this.jobTaskDataList = jobTaskDataList;
	}
	
	
	public void addJobTaskData(JobTaskData pJobTaskData) {
		this.jobTaskDataList.add(pJobTaskData);
	}

	public Long getCriticalCount() {
		return criticalCount;
	}

	public void setCriticalCount(Long criticalCount) {
		this.criticalCount = criticalCount;
	}

	public void addCriticalCount() {
		this.criticalCount++;
	}

	public Long getAckCount() {
		return ackCount;
	}

	public void setAckCount(Long ackCount) {
		this.ackCount = ackCount;
	}

	public void addAckCount() {
		this.ackCount++;
	}

	public Long getNeedAckCount() {
		return needAckCount;
	}

	public void setNeedAckCount(Long needAckCount) {
		this.needAckCount = needAckCount;
	}

	public void addNeedAckCount() {
		this.needAckCount++;
	}

	public Long getUnknownCount() {
		return unknownCount;
	}

	public void setUnknownCount(Long unknownCount) {
		this.unknownCount = unknownCount;
	}

	public void addUnknownCount() {
		this.unknownCount++;
	}

	/**
	 * @return the warningCount
	 */
	public Long getWarningCount() {
		return warningCount;
	}

	/**
	 * @param warningCount
	 *            the warningCount to set
	 */
	public void setWarningCount(Long warningCount) {
		this.warningCount = warningCount;
	}

	public void addWarningCount() {
		this.warningCount++;
	}

	/**
	 * @return the jobDetailsList
	 */
	public List<JobDetails> getJobDetailsList() {
		return jobDetailsList;
	}

	/**
	 * @param jobDetailsList
	 *            the jobDetailsList to set
	 */
	public void setJobDetailsList(List<JobDetails> jobDetailsList) {
		this.jobDetailsList = jobDetailsList;
	}

	public void addJobDetails(JobDetails pJobDetails) {
		this.jobDetailsList.add(pJobDetails);
	}

	public List<Acknowledged> getAcknowledgeList() {
		return acknowledgeList;
	}

	public void setAcknowledgeList(List<Acknowledged> acknowledgeList) {
		this.acknowledgeList = acknowledgeList;
	}
	public void addAllAcknowledgeList(List<Acknowledged> acknowledgeList) {
		this.acknowledgeList.addAll(acknowledgeList);
	}
	
	public String getServerIpAddress() {
		return serverIpAddress;
	}

	public void setServerIpAddress(String serverIpAddress) {
		this.serverIpAddress = serverIpAddress;
	}

	/**
	 * @return the dockerImages
	 */
	public DockerImages getDockerImages() {
		return dockerImages;
	}

	/**
	 * @param dockerImages the dockerImages to set
	 */
	public void setDockerImages(DockerImages dockerImages) {
		this.dockerImages = dockerImages;
	}

	public String getContainerResponse() {
		return containerResponse;
	}

	public void setContainerResponse(String containerResponse) {
		this.containerResponse = containerResponse;
	}

	public String getNvidiaHost() {
		return nvidiaHost;
	}

	public void setNvidiaHost(String nvidiaHost) {
		this.nvidiaHost = nvidiaHost;
	}

	public String getBosunHost() {
		return bosunHost;
	}

	public void setBosunHost(String bosunHost) {
		this.bosunHost = bosunHost;
	}

	public String getGrafanaHost() {
		return grafanaHost;
	}

	public void setGrafanaHost(String grafanaHost) {
		this.grafanaHost = grafanaHost;
	}
	
}
