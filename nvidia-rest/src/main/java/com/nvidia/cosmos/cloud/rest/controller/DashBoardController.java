package com.nvidia.cosmos.cloud.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvidia.cosmos.cloud.dtos.DashBoardDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;
import com.nvidia.cosmos.cloud.services.dashboard.model.DashBoard;
import com.nvidia.cosmos.cloud.services.dashboard.service.DashBoardService;
import com.nvidia.cosmos.cloud.services.user.dao.UserDao;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
public class DashBoardController extends BaseController {

	DashBoardService dashBoardService;
	@Autowired
	Environment environment;

	UserDao userDao;

	public DashBoardController() {
		dashBoardService = factory.getDashBoardService();

	}

	/**
	 * RBAC and tenant rules This API is allowed to Super Admin Super Admin can
	 * get list of dashboard details
	 * 
	 * @param dto
	 * @param request
	 * @return
	 * @throws BaseException
	 */
	private static Logger logger = LoggerFactory.getLogger(DashBoardController.class);

	@Path("/api/dashboard")
	@GET
	@ApiOperation(value = "Get all the dashboard details", notes = "User can get the dashboard details. User with role Super Admin is allowed. ", response = DashBoard.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "All the dashboard details have been listed") })
	@Transactional(rollbackFor = { NotAuthorizeException.class, BaseException.class })
	@RequestMapping(value = "/api/dashboard", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('Super Admin')")
	public ResponseDTO dashBoardDetails(HttpServletRequest request) throws NotAuthorizeException, BaseException {

		logger.debug("Fetching dashboard details ");
		String message = null;
		String statusCode = HttpStatus.OK.toString();

		DashBoard dashBoard = dashBoardService.getDashBoardDetails();

		DashBoardDTO dashBoardDTO = null;
		if (dashBoard != null) {
			dashBoardDTO = new DashBoardDTO(dashBoard.getUsersCount(), dashBoard.getActiveUsers(),
					dashBoard.getNodeCount(), dashBoard.getNodeConnectedCount(), dashBoard.getLoginUsers(),
					dashBoard.getLoginFailedCount());
		}
		message = environment.getProperty("dashboard.details");
		logger.debug("Retrieved all roles.");
		ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		response.setData(dashBoardDTO);
		return response;
	}

}
