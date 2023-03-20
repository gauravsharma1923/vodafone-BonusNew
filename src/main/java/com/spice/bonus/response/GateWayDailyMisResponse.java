package com.spice.bonus.response;

import lombok.Data;

@Data
public class GateWayDailyMisResponse {
	
	  private String mis_date;
	  private String uniq_users;
	  private String tot_trans;
	  
	  private String succ_trans;
	  private String user_abrt_trans;
	  private String tot_sessions;
	  
	  private String err_sess;
	 
//	private String data;
//	private String gw;
//	private String transaction;
	
	
	  public GateWayDailyMisResponse(String mis_date, String uniq_users, String
	  tot_trans, String succ_trans, String user_abrt_trans, String tot_sessions,
	  String err_sess) { 
		  super();
		  this.mis_date = mis_date;
		  this.uniq_users = uniq_users; 
		  this.tot_trans = tot_trans;
		  this.succ_trans = succ_trans;
	  this.user_abrt_trans = user_abrt_trans;
	  this.tot_sessions = tot_sessions;
	  this.err_sess = err_sess;
	  
	  
	  }
	 
//	public GateWayDailyMisResponse(String data, String gw, String transaction) {
//		super();
//		this.data = data;
//		this.gw = gw;
//		this.transaction = transaction;
//		
//		
//		
//	}
}
