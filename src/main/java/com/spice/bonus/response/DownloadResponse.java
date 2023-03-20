package com.spice.bonus.response;

import lombok.Data;

@Data
public class DownloadResponse {
	private String msisdn;
	private String msiType;
	private String circleId;
	
	public DownloadResponse(String msisdn, String msiType, String circleId) {
		super();
		this.msisdn = msisdn;
		this.msiType = msiType;
		this.circleId = circleId;
	}
}
