package com.tresbu.nvidia.common;

/**
 * @author K Anil
 * 
 */
public enum ResponseType {

	JSON("application/json; charset=utf-8");

	private String contentType;

	private ResponseType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}
}
