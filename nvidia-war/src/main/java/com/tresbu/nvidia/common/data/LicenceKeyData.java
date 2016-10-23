package com.tresbu.nvidia.common.data;

public class LicenceKeyData {

	private String customerId;
	private String clusterId;
	private String serialId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	@Override
	public String toString() {
		return "LicenceKeyData [customerId=" + customerId + ", clusterId=" + clusterId + ", serialId=" + serialId + ", getCustomerId()=" + getCustomerId()
				+ ", getClusterId()=" + getClusterId() + ", getNodeId()=" + getSerialId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
