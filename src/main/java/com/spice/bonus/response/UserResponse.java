package com.spice.bonus.response;

import lombok.Data;

@Data
public class UserResponse {	
	private String user_id;
	  private String password;
	  private String user_type;
	  private String circle_id;  
	  private String create_date_time;
	  private String expiry_date_time;
	  private String sam_id;	  
	  private String workorder_id;
	  private String email_id;	  
	  private String msisdn;
	  private String lastLogin;
	  
	  public UserResponse(String user_id, String password, String user_type, String circle_id, String create_date_time,
				String expiry_date_time, String sam_id, String workorder_id, String email_id, String msisdn,
				String lastLogin) {
			super();
			this.user_id = user_id;
			this.password = password;
			this.user_type = user_type;
			this.circle_id = circle_id;
			this.create_date_time = create_date_time;
			this.expiry_date_time = expiry_date_time;
			this.sam_id = sam_id;
			this.workorder_id = workorder_id;
			this.email_id = email_id;
			this.msisdn = msisdn;
			this.lastLogin = lastLogin;
		}
}

