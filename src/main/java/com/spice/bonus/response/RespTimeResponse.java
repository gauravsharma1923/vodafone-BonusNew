package com.spice.bonus.response;

import lombok.Data;

@Data
public class RespTimeResponse {
	private String data;
	private String dt;
	private String countRespTime0;
	private String countRespTime1;
	private String countRespTime2;
	private String countRespTime3;
	public RespTimeResponse(String data, String dt, String countRespTime0, String countRespTime1, String countRespTime2,
			String countRespTime3) {
		super();
		this.data = data;
		this.dt = dt;
		this.countRespTime0 = countRespTime0;
		this.countRespTime1 = countRespTime1;
		this.countRespTime2 = countRespTime2;
		this.countRespTime3 = countRespTime3;
	}
	

}
