package com.spice.bonus.request;

import lombok.Data;

@Data
public class AddUserRequest {
	private String user_id;
	private String password;
	private String user_type;
	private String circle_id;
	private String sam_id;
	private String workorder_id;
	private String email_id;
	private String msisdn;
}
