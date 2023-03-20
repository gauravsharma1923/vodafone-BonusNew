package com.spice.bonus.response;

import lombok.Data;

@Data
public class LoginResponse {
	private String user_id;
	private String password;
	private String user_type;
	private String circle_id;
	private String expiry_date_time;
	private String lastLogin;
	
	public LoginResponse(String user_id, String password, String user_type, String circle_id, String expiry_date_time,
			String lastLogin) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.user_type = user_type;
		this.circle_id = circle_id;
		this.expiry_date_time = expiry_date_time;
		this.lastLogin = lastLogin;
	}  
	
}
