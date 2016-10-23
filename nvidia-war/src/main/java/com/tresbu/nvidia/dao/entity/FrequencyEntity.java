package com.tresbu.nvidia.dao.entity;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the frequency database table.
 * 
 */
public class FrequencyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int frequencyId;

	private String frequency;

	//bi-directional many-to-one association to Alert
	private List<AlertEntity> alerts;

	public FrequencyEntity() {
	}

	public int getFrequencyId() {
		return this.frequencyId;
	}

	public void setFrequencyId(int frequencyId) {
		this.frequencyId = frequencyId;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public List<AlertEntity> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<AlertEntity> alerts) {
		this.alerts = alerts;
	}

	public AlertEntity addAlert(AlertEntity alert) {
		getAlerts().add(alert);
		alert.setFrequency(this);

		return alert;
	}

	public AlertEntity removeAlert(AlertEntity alert) {
		getAlerts().remove(alert);
		alert.setFrequency(null);

		return alert;
	}

}