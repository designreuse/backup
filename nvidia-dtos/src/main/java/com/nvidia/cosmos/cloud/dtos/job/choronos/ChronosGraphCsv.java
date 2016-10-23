package com.nvidia.cosmos.cloud.dtos.job.choronos;

public class ChronosGraphCsv {

	private String node;
	private String jobId;
	private String lastResult;
	private String jobState;
	private Boolean isDeleted;

	/**
	 * @return the node
	 */
	public String getNode() {
		return node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	public void setNode(String node) {
		this.node = node;
	}

	/**
	 * @return the jobId
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * @param jobId
	 *            the jobId to set
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return the lastResult
	 */
	public String getLastResult() {
		return lastResult;
	}

	/**
	 * @param lastResult
	 *            the lastResult to set
	 */
	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
	}

	/**
	 * @return the jobState
	 */
	public String getJobState() {
		return jobState;
	}

	/**
	 * @param jobState
	 *            the jobState to set
	 */
	public void setJobState(String jobState) {
		this.jobState = jobState;
	}

	/**
	 * @return the isDeleted
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public ChronosGraphCsv() {
	}

	/**
	 * @param node
	 * @param jobId
	 * @param lastResult
	 * @param jobState
	 */
	public ChronosGraphCsv(String node, String jobId, String lastResult, String jobState) {
		super();
		this.node = node;
		this.jobId = jobId;
		this.lastResult = lastResult;
		this.jobState = jobState;
	}

}
