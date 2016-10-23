package com.tresbu.nvidia.dao.entity;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the schedule database table.
 * 
 */
public class ScheduleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int scheduleId;

	private String schedule;

	//bi-directional many-to-one association to Alert
	private List<AlertEntity> alerts;

	public ScheduleEntity() {
	}

	public int getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getSchedule() {
		return this.schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public List<AlertEntity> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<AlertEntity> alerts) {
		this.alerts = alerts;
	}

	public AlertEntity addAlert(AlertEntity alert) {
		getAlerts().add(alert);
		alert.setSchedule(this);

		return alert;
	}

	public AlertEntity removeAlert(AlertEntity alert) {
		getAlerts().remove(alert);
		alert.setSchedule(null);

		return alert;
	}

}