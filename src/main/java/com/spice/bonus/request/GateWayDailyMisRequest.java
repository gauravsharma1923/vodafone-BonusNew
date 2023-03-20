package com.spice.bonus.request;

import lombok.Data;

@Data
public class GateWayDailyMisRequest {
	private String startdate;
	private String enddate;
	private String circle;
}
