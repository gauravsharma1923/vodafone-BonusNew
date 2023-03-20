package com.spice.bonus.request;

import lombok.Data;

@Data
public class EditUserRequest {
	private String userId;
	private String password;
	private String userType;
	private String circleId;
	private String samId;
	private String workorderId;
	private String emailId;
	private String msisdn;
	private String createDate;
	private String expiryDate;
	private String lastLoginDate;

}
