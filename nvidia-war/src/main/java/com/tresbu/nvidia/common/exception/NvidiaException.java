package com.tresbu.nvidia.common.exception;

import static com.tresbu.nvidia.common.AppConstant.EXCEPTION_MESSAGE_FORMAT;

public class NvidiaException extends Exception {

	private static final long serialVersionUID = -587078681117382422L;

	private String mType;
	private String mSummary;
	private String mDescription;

	public NvidiaException(String pMessage) {
		super(pMessage);
	}

	public NvidiaException(Exception pException) {
		super(pException);

		if (pException instanceof NvidiaException) {
			NvidiaException eagleException = (NvidiaException) pException;
			mType = eagleException.getType();
			mSummary = eagleException.getSummary();
			mDescription = eagleException.getDescription();
		}
	}

	public NvidiaException(String pMessage, Exception pException) {
		super(pMessage, pException);
	}

	public NvidiaException(String pApplicationMessage, String pExceptionMessage) {
		super(String.format(EXCEPTION_MESSAGE_FORMAT, pApplicationMessage, pExceptionMessage));
	}

	public NvidiaException(String pApplicationMessage, String pExceptionMessage, Exception pException) {
		super(String.format(EXCEPTION_MESSAGE_FORMAT, pApplicationMessage, pExceptionMessage), pException);
	}

	public NvidiaException(String pType, String pSummary, String pDescription) {
		super(String.format(EXCEPTION_MESSAGE_FORMAT, pSummary, pDescription));

		this.mType = pType;
		this.mSummary = pSummary;
		this.mDescription = pDescription;
	}

	public String getType() {
		return mType;
	}

	public String getSummary() {
		return mSummary;
	}

	public String getDescription() {
		return mDescription;
	}

}
