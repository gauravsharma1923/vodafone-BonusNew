package com.spice.bonus.response;

import lombok.Data;

@Data
public class TpsResponse {
	private String data;
	private String dt;
	private String peakTps;
	private String avgTps;
	private String minTps;
	public TpsResponse(String data, String dt, String peakTps, String avgTps, String minTps) {
		super();
		this.data = data;
		this.dt = dt;
		this.peakTps = peakTps;
		this.avgTps = avgTps;
		this.minTps = minTps;
	}
	public TpsResponse(String dt, String peakTps, String avgTps, String minTps) {
		super();
		this.dt = dt;
		this.peakTps = peakTps;
		this.avgTps = avgTps;
		this.minTps = minTps;
	}
	
}
