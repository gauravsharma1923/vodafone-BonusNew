package com.spice.bonus.request;

import lombok.Data;

@Data
public class ChangePassword {
	private String userId;
	private String oldPassword;
	private String newPassword;
}
