package com.tresbu.nvidia.dao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the attempts database table.
 * 
 */
public class AttemptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int attemptId;

	private String attempts;

	// bi-directional many-to-one association to Alert
	private List<AlertEntity> alerts;

	public AttemptEntity() {
	}

	public int getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(int attemptsId) {
		this.attemptId = attemptsId;
	}

	public String getAttempts() {
		return this.attempts;
	}

	public void setAttempts(String attempts) {
		this.attempts = attempts;
	}

	public List<AlertEntity> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<AlertEntity> alerts) {
		this.alerts = alerts;
	}

	public AlertEntity addAlert(AlertEntity alert) {
		getAlerts().add(alert);
		alert.setAttempt(this);

		return alert;
	}

	public AlertEntity removeAlert(AlertEntity alert) {
		getAlerts().remove(alert);
		alert.setAttempt(null);

		return alert;
	}

}