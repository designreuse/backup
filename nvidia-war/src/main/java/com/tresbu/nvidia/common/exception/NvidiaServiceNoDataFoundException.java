package com.tresbu.nvidia.common.exception;

public class NvidiaServiceNoDataFoundException extends NvidiaServiceException {

	private static final long serialVersionUID = 541490258472070202L;

	public NvidiaServiceNoDataFoundException(String pMessage) {
		super(pMessage);
	}

	public NvidiaServiceNoDataFoundException(Exception pException) {
		super(pException);
	}

	public NvidiaServiceNoDataFoundException(String pMessage, Exception pException) {
		super(pMessage, pException);
	}

	public NvidiaServiceNoDataFoundException(String pApplicationMessage, String pErrorMessage, Exception pException) {
		super(pApplicationMessage, pErrorMessage, pException);
	}

	public NvidiaServiceNoDataFoundException(String pType, String pSummary, String pDescription) {
		super(pType, pSummary, pDescription);
	}
}
