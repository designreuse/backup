package com.tresbu.nvidia.common.exception;

public class NvidiaDaoException extends NvidiaException {

	private static final long serialVersionUID = 4950876289552303004L;

	public NvidiaDaoException(Exception pException) {
		super(pException);
	}

	public NvidiaDaoException(String pMessage, Exception pException) {
		super(pMessage, pException);
	}

	public NvidiaDaoException(String pApplicationMessage, String pExceptionMessage, Exception pException) {
		super(pApplicationMessage, pExceptionMessage, pException);
	}

	public NvidiaDaoException(String pApplicationMessage, String pExceptionMessage) {
		super(pApplicationMessage, pExceptionMessage);
	}

	public NvidiaDaoException(String pMessage) {
		super(pMessage);
	}

	public NvidiaDaoException(String pType, String pSummary, String pDescription) {
		super(pType, pSummary, pDescription);
	}

}
