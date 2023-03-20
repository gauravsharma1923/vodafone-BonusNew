package com.spice.bonus.response;

import lombok.Data;

@Data
public class GateWayHourlyResponse {
	
	  private String mis_date;
	  private String mis_hour;
	  private String uniq_users;
	  private String tot_trans;
	  
	  private String succ_trans;
	  private String user_abrt_trans;
	  private String tot_sessions;
	  
	  private String err_sess;
	 

	  public GateWayHourlyResponse(String mis_date, String mis_hour, String uniq_users, String
	  tot_trans, String succ_trans, String user_abrt_trans, String tot_sessions,
	  String err_sess) { 
		  super();
		  this.mis_date = mis_date;
		  this.mis_hour = mis_hour;
		  this.uniq_users = uniq_users; 
		  this.tot_trans = tot_trans;
		  this.succ_trans = succ_trans;
	  this.user_abrt_trans = user_abrt_trans;
	  this.tot_sessions = tot_sessions;
	  this.err_sess = err_sess;
	  
	  
	  }

}
