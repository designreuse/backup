package com.nvidia.cosmos.cloud.dtos;

import java.util.Arrays;


public class EulaDTO implements BaseDTO {
	public static final int MAX_LENGTH_NAME = 100;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String name;

	private byte[] eulaContentByteArray;

	private String eulaContent;

	private String eulaFileName;

	public EulaDTO() {
		super();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the eulaContentByteArray
	 */
	public byte[] getEulaContentByteArray() {
		return eulaContentByteArray;
	}

	/**
	 * @param eulaContentByteArray
	 *            the eulaContentByteArray to set
	 */
	public void setEulaContentByteArray(byte[] eulaContentByteArray) {
		this.eulaContentByteArray = eulaContentByteArray;
	}

	/**
	 * @return the eulaContent
	 */
	public String getEulaContent() {
		return eulaContent;
	}

	/**
	 * @param eulaContent
	 *            the eulaContent to set
	 */
	public void setEulaContent(String eulaContent) {
		this.eulaContent = eulaContent;
	}

	public EulaDTO(String name, byte[] eulaContentByteArray, String eulaContent, String eulaFileName) {
		super();
		this.name = name;
		this.eulaContentByteArray = eulaContentByteArray;
		this.eulaContent = eulaContent;
		this.eulaFileName = eulaFileName;
	}

	/**
	 * @return the eulaFileName
	 */
	public String getEulaFileName() {
		return eulaFileName;
	}

	/**
	 * @param eulaFileName
	 *            the eulaFileName to set
	 */
	public void setEulaFileName(String eulaFileName) {
		this.eulaFileName = eulaFileName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EulaDTO [name=" + name + ", eulaContentByteArray=" + Arrays.toString(eulaContentByteArray) + ", eulaContent=" + eulaContent
				+ ",eulaFileName=" + eulaFileName + "]";
	}
	
}
