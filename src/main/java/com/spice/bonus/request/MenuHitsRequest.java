package com.spice.bonus.request;

import lombok.Data;

@Data
public class MenuHitsRequest {
	private String startDate;
	private String endDate;
	private String circleId;
	private String language;
	private String type;
}
