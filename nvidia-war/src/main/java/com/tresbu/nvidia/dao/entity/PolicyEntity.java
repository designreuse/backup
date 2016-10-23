package com.tresbu.nvidia.dao.entity;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the policy database table.
 * 
 */
public class PolicyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int policyId;

	private String policy;

	//bi-directional many-to-one association to Alert
	private List<AlertEntity> alerts;

	public PolicyEntity() {
	}

	public int getPolicyId() {
		return this.policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicy() {
		return this.policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public List<AlertEntity> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<AlertEntity> alerts) {
		this.alerts = alerts;
	}

	public AlertEntity addAlert(AlertEntity alert) {
		getAlerts().add(alert);
		alert.setPolicy(this);

		return alert;
	}

	public AlertEntity removeAlert(AlertEntity alert) {
		getAlerts().remove(alert);
		alert.setPolicy(null);

		return alert;
	}

}