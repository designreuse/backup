package com.tresbu.nvidia.common.exception;

public class NvidiaDBDaoException extends NvidiaDaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7073887476771819529L;

	public NvidiaDBDaoException(Exception pException) {
		super(pException);
	}

	public NvidiaDBDaoException(String pMessage, Exception pException) {
		super(pMessage, pException);
	}

	public NvidiaDBDaoException(String pApplicationMessage, String pExceptionMessage, Exception pException) {
		super(pApplicationMessage, pExceptionMessage, pException);
	}

	public NvidiaDBDaoException(String pApplicationMessage, String pExceptionMessage) {
		super(pApplicationMessage, pExceptionMessage);
	}

	public NvidiaDBDaoException(String pMessage) {
		super(pMessage);
	}

	public NvidiaDBDaoException(String pType, String pSummary, String pDescription) {
		super(pType, pSummary, pDescription);
	}
}
