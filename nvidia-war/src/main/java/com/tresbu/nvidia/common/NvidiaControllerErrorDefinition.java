package com.tresbu.nvidia.common;

import com.tresbu.nvidia.common.ErrorConstant.NVIDIA_ERROR_TYPES;
import static com.tresbu.nvidia.common.ErrorConstant.NVIDIA_ERROR_TYPES.USER;
import com.tresbu.nvidia.common.ErrorConstant.NvidiaErrorDefinition;
import static com.tresbu.nvidia.common.NvidiaErrorConstant.INVALID_LOGIN_CREDENTIALS;
import static com.tresbu.nvidia.common.NvidiaErrorConstant.NODE_ALREADY_EXIST;
import static com.tresbu.nvidia.common.NvidiaErrorConstant.CLUSTER_ALREADY_EXIST;
import static com.tresbu.nvidia.common.NvidiaErrorConstant.CUSTOMER_ALREADY_EXIST;
import static com.tresbu.nvidia.common.NvidiaErrorConstant.JOB_ALREADY_EXIST;
import static com.tresbu.nvidia.common.NvidiaErrorConstant.CLUSTER_DATA_NOT_FOUND;
import static com.tresbu.nvidia.common.NvidiaErrorConstant.CUSTOMER_DATA_NOT_FOUND;
import static com.tresbu.nvidia.common.NvidiaErrorConstant.NODE_DATA_NOT_FOUND;
import static com.tresbu.nvidia.common.NvidiaErrorConstant.JOB_DATA_NOT_FOUND;
import static com.tresbu.nvidia.common.NvidiaErrorConstant.LICENCE_KEY_NOT_FOUND;
import static com.tresbu.nvidia.common.NvidiaErrorConstant.TOKEN_ALREADY_EXIST;

import static com.tresbu.nvidia.common.NvidiaErrorConstant.AUTH_TOKEN_NOT_FOUND;

public enum NvidiaControllerErrorDefinition implements NvidiaErrorDefinition {

	NVIDIA_LOGIN_DTAT_NOT_FOUND(USER, INVALID_LOGIN_CREDENTIALS, "%s"),
	
	NVIDIA_CLUSTER_DATA_NOT_FOUND(USER,CLUSTER_DATA_NOT_FOUND, "%s"),
	
	NVIDIA_CUSTOMER_DATA_NOT_FOUND(USER,CUSTOMER_DATA_NOT_FOUND,"%s"),
	
	NVIDIA_NODE_DATA_NOT_FOUND(USER,NODE_DATA_NOT_FOUND,"%s"),
	
	NVIDIA_JOB_DATA_NOT_FOUND(USER,JOB_DATA_NOT_FOUND,"%s"),
	
	NVIDIA_AUTH_TOKEN_NOT_FOUND(USER,AUTH_TOKEN_NOT_FOUND,"%s"),
	
	NVIDIA_LICENCE_KEY_NOT_FOUND(USER,LICENCE_KEY_NOT_FOUND,"%s"),

	NVIDIA_CLUSTER_ALREADY_EXIST(USER, CLUSTER_ALREADY_EXIST, "%s"),

	NVIDIA_NODE_ALREADY_EXIST(USER, NODE_ALREADY_EXIST, "%s"),
	
	NVIDIA_JOB_ALREADY_EXIST(USER, JOB_ALREADY_EXIST, "%s"),
	
	NVIDIA_TOKEN_ALREADY_EXIST(USER,TOKEN_ALREADY_EXIST,"%s"),

	NVIDIA_CUSTOMER_ALREADY_EXIST(USER, CUSTOMER_ALREADY_EXIST, "%s");

	private NVIDIA_ERROR_TYPES errorType;
	private String summary;
	private String description;

	NvidiaControllerErrorDefinition(NVIDIA_ERROR_TYPES errorType, String summary, String description) {
		this.errorType = errorType;
		this.summary = summary;
		this.description = description;
	}

	public String getDescription(String descriptionDetail) {
		return String.format(description, descriptionDetail);
	}

	public String getSummary() {
		return summary;
	}

	public String getType() {
		return errorType.getType();
	}

}
