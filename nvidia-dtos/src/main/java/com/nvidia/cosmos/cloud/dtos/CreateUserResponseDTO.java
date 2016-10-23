package com.nvidia.cosmos.cloud.dtos;



public class CreateUserResponseDTO implements BaseDTO{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	private String userName;
	private String customerName;
	private Long uId;
	private Long gId;
	private String roleName;
	
	
	public CreateUserResponseDTO(){
		super();
	}
	
	public CreateUserResponseDTO(String id, String name,String email,String userName,Long uId,Long gId){
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.userName = userName;
		this.uId = uId;
		this.gId = gId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getuId() {
		return uId;
	}

	public void setuId(Long uId) {
		this.uId = uId;
	}

	public Long getgId() {
		return gId;
	}

	public void setgId(Long gId) {
		this.gId = gId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

}
