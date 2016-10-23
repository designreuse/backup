package com.tresbu.nvidia.common.exception;

public class NvidiaDaoNoDataFoundException extends NvidiaDaoException {

	private static final long serialVersionUID = -1087602334681350440L;

	public NvidiaDaoNoDataFoundException(Exception pException) {
		super(pException);
	}

	public NvidiaDaoNoDataFoundException(String pMessage, Exception pException) {
		super(pMessage, pException);
	}

	public NvidiaDaoNoDataFoundException(String pApplicationMessage, String pExceptionMessage, Exception pException) {
		super(pApplicationMessage, pExceptionMessage, pException);
	}

	public NvidiaDaoNoDataFoundException(String pApplicationMessage, String pExceptionMessage) {
		super(pApplicationMessage, pExceptionMessage);
	}

	public NvidiaDaoNoDataFoundException(String pMessage) {
		super(pMessage);
	}

	public NvidiaDaoNoDataFoundException(String pType, String pSummary, String pDescription) {
		super(pType, pSummary, pDescription);
	}

}
