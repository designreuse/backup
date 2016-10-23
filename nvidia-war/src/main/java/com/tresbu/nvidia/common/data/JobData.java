package com.tresbu.nvidia.common.data;

public class JobData {

	private int jobDataId;
	private String clusterID;
	private String jobName;
	private String nodeName;
	private String lasRun;
	private String nextRun;
	private String status;
	private String option;

	public int getJobDataId() {
		return jobDataId;
	}

	public void setJobDataId(int jobDataId) {
		this.jobDataId = jobDataId;
	}

	public String getClusterID() {
		return clusterID;
	}

	public void setClusterID(String clusterID) {
		this.clusterID = clusterID;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getLasRun() {
		return lasRun;
	}

	public void setLasRun(String lasRun) {
		this.lasRun = lasRun;
	}

	public String getNextRun() {
		return nextRun;
	}

	public void setNextRun(String nextRun) {
		this.nextRun = nextRun;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

}
