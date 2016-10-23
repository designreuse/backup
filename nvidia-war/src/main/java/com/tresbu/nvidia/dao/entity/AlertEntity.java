package com.tresbu.nvidia.dao.entity;

import java.io.Serializable;

/**
 * The persistent class for the alert database table.
 * 
 */
public class AlertEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int alertId;
	private int policyId;
	private int scheduleId;
	private int attemptsId;
	private int frequencyId;

	// bi-directional many-to-one association to Attempt
	private AttemptEntity attempt;

	// bi-directional many-to-one association to Cluster
	private ClusterEntity cluster;

	// bi-directional many-to-one association to Frequency
	private FrequencyEntity frequency;

	// bi-directional many-to-one association to Node
	private NodeEntity node;

	// bi-directional many-to-one association to Policy
	private PolicyEntity policy;

	// bi-directional many-to-one association to Schedule
	private ScheduleEntity schedule;

	public AlertEntity() {
	}

	public int getAlertId() {
		return alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public AttemptEntity getAttempt() {
		return this.attempt;
	}

	public void setAttempt(AttemptEntity attempt) {
		this.attempt = attempt;
	}

	public ClusterEntity getCluster() {
		return this.cluster;
	}

	public void setCluster(ClusterEntity cluster) {
		this.cluster = cluster;
	}

	public FrequencyEntity getFrequency() {
		return this.frequency;
	}

	public void setFrequency(FrequencyEntity frequency) {
		this.frequency = frequency;
	}

	public NodeEntity getNode() {
		return this.node;
	}

	public void setNode(NodeEntity node) {
		this.node = node;
	}

	public PolicyEntity getPolicy() {
		return this.policy;
	}

	public void setPolicy(PolicyEntity policy) {
		this.policy = policy;
	}

	public ScheduleEntity getSchedule() {
		return this.schedule;
	}

	public void setSchedule(ScheduleEntity schedule) {
		this.schedule = schedule;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getAttemptsId() {
		return attemptsId;
	}

	public void setAttemptsId(int attemptsId) {
		this.attemptsId = attemptsId;
	}

	public int getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(int frequencyId) {
		this.frequencyId = frequencyId;
	}

}