package com.spice.bonus.response;

import lombok.Data;

@Data
public class ViewResponse {
	private String user_id;
	private String email_id;
	private String msisdn;
	private String lastLogin;
	private String expiry_date_time;
	
	public ViewResponse(String user_id, String email_id, String msisdn, String lastLogin, String expiry_date_time) {
		super();
		this.user_id = user_id;
		this.email_id = email_id;
		this.msisdn = msisdn;
		this.lastLogin = lastLogin;
		this.expiry_date_time = expiry_date_time;
	}
}
