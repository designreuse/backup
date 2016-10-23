package com.nvidia.cosmos.cloud.rest.util;

public class LicenceKey {

	private String customerName;
	private String clusterName;
	private String nodeName;

	/**
	 * 
	 */
	public LicenceKey() {
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the clusterName
	 */
	public String getClusterName() {
		return clusterName;
	}

	/**
	 * @param clusterName
	 *            the clusterName to set
	 */
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * @param nodeName
	 *            the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LicenceKey [customerName=" + customerName + ", clusterName=" + clusterName + ", nodeName=" + nodeName + "]";
	}

	/**
	 * @param customerName
	 * @param clusterName
	 * @param nodeName
	 */
	public LicenceKey(String customerName, String clusterName, String nodeName) {
		super();
		this.customerName = customerName;
		this.clusterName = clusterName;
		this.nodeName = nodeName;
	}

}
