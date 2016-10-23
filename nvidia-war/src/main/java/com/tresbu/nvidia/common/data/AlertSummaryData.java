package com.tresbu.nvidia.common.data;

public class AlertSummaryData {

	private String clusterName;
	private String alertMessage;
	private String alertDateAndTime;
	private String alertTime;

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

	public String getAlertDateAndTime() {
		return alertDateAndTime;
	}

	public void setAlertDateAndTime(String alertDateAndTime) {
		this.alertDateAndTime = alertDateAndTime;
	}

	public String getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(String alertTime) {
		this.alertTime = alertTime;
	}

}
