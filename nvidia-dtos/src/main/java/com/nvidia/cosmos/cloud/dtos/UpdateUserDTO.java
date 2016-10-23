package com.nvidia.cosmos.cloud.dtos;


public class UpdateUserDTO implements BaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	
	private String uId;
	private String gId;
	
	
	public UpdateUserDTO() {
		super();
	}
	public UpdateUserDTO(String id, String uId, String gId) {
		super();
		this.id = id;
		this.uId = uId;
		this.gId = gId;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
	
	
	
	
	
	

}
