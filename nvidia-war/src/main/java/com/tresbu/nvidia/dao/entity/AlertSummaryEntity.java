package com.tresbu.nvidia.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the alert_summary database table.
 * 
 */
public class AlertSummaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int alertSummaryId;

	private Timestamp alertDate;

	private String alertMessage;

	// bi-directional many-to-one association to Cluster
	private ClusterEntity cluster;

	// bi-directional many-to-one association to Node
	private NodeEntity node;

	private String alertTime;

	public AlertSummaryEntity() {
	}

	public int getAlertSummaryId() {
		return alertSummaryId;
	}

	public void setAlertSummaryId(int alertSummaryId) {
		this.alertSummaryId = alertSummaryId;
	}

	public Timestamp getAlertDate() {
		return this.alertDate;
	}

	public void setAlertDate(Timestamp alertDate) {
		this.alertDate = alertDate;
	}

	public String getAlertMessage() {
		return this.alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

	public ClusterEntity getCluster() {
		return this.cluster;
	}

	public void setCluster(ClusterEntity cluster) {
		this.cluster = cluster;
	}

	public NodeEntity getNode() {
		return this.node;
	}

	public void setNode(NodeEntity node) {
		this.node = node;
	}

	public String getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(String alertTime) {
		this.alertTime = alertTime;
	}

}