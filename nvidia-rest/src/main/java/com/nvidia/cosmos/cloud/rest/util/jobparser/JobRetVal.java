package com.nvidia.cosmos.cloud.rest.util.jobparser;

public class JobRetVal {

	private String reason;
	private String data;
	private String status;
	private String message;
	private String jobApiType;

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public JobRetVal() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the jobApiType
	 */
	public String getJobApiType() {
		return jobApiType;
	}

	/**
	 * @param jobApiType
	 *            the jobApiType to set
	 */
	public void setJobApiType(String jobApiType) {
		this.jobApiType = jobApiType;
	}

	/**
	 * 
	 * @param reason
	 * @param data
	 * @param status
	 * @param message
	 * @param jobApiType
	 */
	public JobRetVal(String reason, String data, String status, String message, String jobApiType) {
		super();
		this.reason = reason;
		this.data = data;
		this.status = status;
		this.message = message;
		this.jobApiType = jobApiType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobRetVal [reason=" + reason + ", data=" + data + ", status=" + status + ", message=" + message + ", jobApiType=" + jobApiType + "]";
	}

}
