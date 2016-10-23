package com.tresbu.nvidia.dao.entity;

import java.io.Serializable;

import com.tresbu.nvidia.common.data.NvidiaData;


/**
 * The persistent class for the customer database table.
 * 
 */
public class CustomerEntity extends NvidiaData implements Serializable {
	private static final long serialVersionUID = 1L;

	private String customerId;

	private String email;

	private String password;

	private String username;

	//bi-directional one-to-one association to Cluster
	private ClusterEntity cluster;

	public CustomerEntity() {
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ClusterEntity getCluster() {
		return this.cluster;
	}

	public void setCluster(ClusterEntity cluster) {
		this.cluster = cluster;
	}

}