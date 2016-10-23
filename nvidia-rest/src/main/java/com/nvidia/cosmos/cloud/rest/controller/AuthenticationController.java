package com.nvidia.cosmos.cloud.rest.controller;

import org.apache.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.dtos.UserAuthDTO;

@RestController
public class AuthenticationController extends BaseController {
	@RequestMapping(value = AUTHENTICATE_URL, method = RequestMethod.POST)
	public ResponseDTO authenticate() {

		Authentication resultOfAuthentication = SecurityContextHolder.getContext().getAuthentication();
		ResponseDTO response = new ResponseDTO("Success ", HttpStatus.SC_OK);
		response.setData((UserAuthDTO) resultOfAuthentication.getPrincipal());

		return response;

	}
}
