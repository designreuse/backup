package com.nvidia.cosmos.cloud.services.dashboard.model;

import com.nvidia.cosmos.cloud.common.model.AbstractModel;

public class DashBoard extends AbstractModel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long usersCount=0L;
	private Long activeUsers=0L;
	private Long nodeCount=0L;
	private Long nodeConnectedCount=0L;
	private Long loginUsers=0L;
	private Long loginFailedCount=0L;
	
	public Long getLoginFailedCount() {
		return loginFailedCount;
	}
	public void setLoginFailedCount(Long loginFailedCount) {
		this.loginFailedCount = loginFailedCount;
	}
	public Long getUsersCount() {
		return usersCount;
	}
	public void setUsersCount(Long usersCount) {
		this.usersCount = usersCount;
	}
	public Long getActiveUsers() {
		return activeUsers;
	}
	public void setActiveUsers(Long activeUsers) {
		this.activeUsers = activeUsers;
	}
	public Long getNodeCount() {
		return nodeCount;
	}
	public void setNodeCount(Long nodeCount) {
		this.nodeCount = nodeCount;
	}
	public Long getNodeConnectedCount() {
		return nodeConnectedCount;
	}
	public void setNodeConnectedCount(Long nodeConnectedCount) {
		this.nodeConnectedCount = nodeConnectedCount;
	}
	public Long getLoginUsers() {
		return loginUsers;
	}
	public void setLoginUsers(Long loginUsers) {
		this.loginUsers = loginUsers;
	}
	
	
}
