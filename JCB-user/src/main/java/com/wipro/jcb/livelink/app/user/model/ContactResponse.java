package com.wipro.jcb.livelink.app.user.model;

public class ContactResponse {
	
	private String email;
	private String mobileNumber;
	
	public ContactResponse(){}

	public ContactResponse(String email, String mobileNumber) {
		super();
		this.email = email;
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	

}
