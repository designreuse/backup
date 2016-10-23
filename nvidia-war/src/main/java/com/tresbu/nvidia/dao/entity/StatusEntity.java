package com.tresbu.nvidia.dao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the status database table.
 * 
 */
public class StatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int statusId;

	private String status;

	// bi-directional many-to-one association to Job
	private List<JobEntity> jobs;

	// bi-directional one-to-one association to Node
	private NodeEntity node;

	public StatusEntity() {
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<JobEntity> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<JobEntity> jobs) {
		this.jobs = jobs;
	}

	public JobEntity removeJob(JobEntity job) {
		getJobs().remove(job);
		job.setStatus(null);

		return job;
	}

	public NodeEntity getNode() {
		return this.node;
	}

	public void setNode(NodeEntity node) {
		this.node = node;
	}

}