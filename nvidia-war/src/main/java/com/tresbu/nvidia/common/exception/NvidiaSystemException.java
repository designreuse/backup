package com.tresbu.nvidia.common.exception;

import static com.tresbu.nvidia.common.AppConstant.EXCEPTION_MESSAGE_FORMAT;

public class NvidiaSystemException extends NvidiaException {

	private static final long serialVersionUID = -7067312569084970047L;

	public NvidiaSystemException(String pMessage) {
		super(pMessage);
	}

	public NvidiaSystemException(Exception pException) {
		super(pException);
	}

	public NvidiaSystemException(String pApplicationMessage, String pExceptionMessage) {
		super(String.format(EXCEPTION_MESSAGE_FORMAT, pApplicationMessage, pExceptionMessage));
	}

	public NvidiaSystemException(String pMessage, Exception pException) {
		super(pMessage, pException);
	}

	public NvidiaSystemException(String pApplicationMessage, String pExceptionMessage, Exception pException) {
		super(pApplicationMessage, pExceptionMessage, pException);
	}
}
