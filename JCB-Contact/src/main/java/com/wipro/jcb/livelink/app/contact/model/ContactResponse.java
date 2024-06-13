package com.wipro.jcb.livelink.app.contact.model;

public class ContactResponse {
	
	private int id;
	private String email;
	private String mobileNumber;
	
	public ContactResponse(){}

	public ContactResponse(int id, String email, String mobileNumber) {
		super();
		this.id = id;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
