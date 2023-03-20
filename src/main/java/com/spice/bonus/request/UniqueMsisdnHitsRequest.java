package com.spice.bonus.request;

import lombok.Data;

@Data
public class UniqueMsisdnHitsRequest {
	private String startDate;
	private String endDate;
	private String requestType;
}
