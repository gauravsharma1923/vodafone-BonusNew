package com.spice.bonus.request;

import lombok.Data;

@Data
public class TransactionReportRequest1 {
	private String startdate;
	private String enddate;
	private String hour;
	private String reportingType;
	private String attackType;
}
