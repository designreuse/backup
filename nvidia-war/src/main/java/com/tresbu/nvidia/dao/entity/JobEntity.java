package com.tresbu.nvidia.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.tresbu.nvidia.common.data.NvidiaData;
import com.tresbu.nvidia.common.DateUtil;

/**
 * The persistent class for the job database table.
 * 
 */
public class JobEntity extends NvidiaData implements Serializable {
	private static final long serialVersionUID = 1L;

	private int jobId;

	private String jobName;

	private String nodeId;

	private Timestamp lastRunDate;

	private Timestamp nextRunDate;

	// bi-directional many-to-one association to Status

	private String status;

	// bi-directional many-to-one association to Node
	private NodeEntity node;

	public JobEntity() {
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public int getJobId() {
		return this.jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Timestamp getLastRunDate() {
		return this.lastRunDate;
	}

	public void setLastRunDate(String lastRunDate) {

		this.lastRunDate = DateUtil.convertStringToTimeStamp(lastRunDate);
	}

	public Timestamp getNextRunDate() {
		return this.nextRunDate;
	}

	public void setNextRunDate(String nextRunDate) {
		this.nextRunDate = DateUtil.convertStringToTimeStamp(nextRunDate);
	}

	public NodeEntity getNode() {
		return this.node;
	}

	public void setNode(NodeEntity node) {
		this.node = node;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "JobEntity [jobId=" + jobId + ", jobName=" + jobName + ", lastRunDate=" + lastRunDate + ", nextRunDate=" + nextRunDate + ", node="
				+ node + ", status=" + status + "]";
	}

}