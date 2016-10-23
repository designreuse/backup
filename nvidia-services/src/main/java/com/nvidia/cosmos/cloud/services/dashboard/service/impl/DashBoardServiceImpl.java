package com.nvidia.cosmos.cloud.services.dashboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.nvidia.cosmos.cloud.services.dashboard.model.DashBoard;
import com.nvidia.cosmos.cloud.services.dashboard.service.DashBoardService;
import com.nvidia.cosmos.cloud.services.node.dao.NodeDao;
import com.nvidia.cosmos.cloud.services.user.dao.UserDao;
import com.nvidia.cosmos.cloud.services.userauth.dao.UserAuthDao;
@Service("dashBoardService")
public class DashBoardServiceImpl implements DashBoardService {
	
	@Autowired
    private UserDao userDao;
	@Autowired
	private NodeDao nodeDao;
	@Autowired
	Environment environment;
	private int maxAttempts=3;
	
	@Autowired
	private UserAuthDao userAuthDao;
	
	private void init(){
		if(environment.getProperty("max.failed.login.attempts.allowed")!=null)
			maxAttempts = Integer.parseInt(environment.getProperty("max.failed.login.attempts.allowed"));
	}
	
	
	
	
	@Override
	public DashBoard getDashBoardDetails() {
		init();
		DashBoard dashBoard =new DashBoard();
		dashBoard.setUsersCount(userDao.getUserCount());
		dashBoard.setActiveUsers(userDao.getActiveUserCount());
		dashBoard.setNodeCount(nodeDao.getNodeCount());
		dashBoard.setNodeConnectedCount(nodeDao.getNodeConnectedCount());
		dashBoard.setLoginUsers(userAuthDao.getLoginUsers());
		dashBoard.setLoginFailedCount(userDao.getLoginFailedCount(maxAttempts));
		
		return dashBoard;
	}

}
