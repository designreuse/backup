package com.nvidia.cosmos.cloud.rest.common.util;

/**
 * @author K Anil
 * 
 */
public enum ResponseType {

	JSON("application/json; charset=utf-8"),
	TEXT("text/plain; charset=utf-8"),
	URLENCODE("application/x-www-form-urlencoded");

	private String contentType;

	private ResponseType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}
}
