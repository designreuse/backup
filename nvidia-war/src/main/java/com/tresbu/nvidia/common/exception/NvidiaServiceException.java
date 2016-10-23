package com.tresbu.nvidia.common.exception;

public class NvidiaServiceException extends NvidiaException {

	private static final long serialVersionUID = -1557650542096975774L;

	public NvidiaServiceException(Exception pException) {
		super(pException);
	}

	public NvidiaServiceException(String pMessage, Exception pException) {
		super(pMessage, pException);
	}

	public NvidiaServiceException(String pApplicationMessage, String pExceptionMessage, Exception pException) {
		super(pApplicationMessage, pExceptionMessage, pException);
	}

	public NvidiaServiceException(String pApplicationMessage, String pExceptionMessage) {
		super(pApplicationMessage, pExceptionMessage);
	}

	public NvidiaServiceException(String pMessage) {
		super(pMessage);
	}

	public NvidiaServiceException(Throwable e) {
		super(e.getMessage());
	}
	public NvidiaServiceException(String pType, String pSummary, String pDescription) {
		super(pType, pSummary, pDescription);
	}
}
