package com.nvidia.cosmos.cloud.dtos;

public class EulaUpdatedOnNodeDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String eulaAccepted;

	public String getEulaAccepted() {
		return eulaAccepted;
	}

	public void setEulaAccepted(String eulaAccepted) {
		this.eulaAccepted = eulaAccepted;
	}

	public EulaUpdatedOnNodeDTO() {
		super();
	}

	public EulaUpdatedOnNodeDTO(String eulaAccepted) {
		super();
		this.eulaAccepted = eulaAccepted;
	}
	
	
}
