
package com.spice.bonus.response;

import lombok.Data;

@Data
public class GateWayHourlyCircleResponse {
	//date_time,mis_hour,uniq_msisdn,session_id,msisdn,succ_trans,abort_trans,err_succ
	  private String date_time;
	  private String mis_hour;
	  private String uniq_msisdn;
	  private String session_id;
	  
	  private String msisdn;
	  private String succ_trans;
	  private String abort_trans;
	  
	  private String err_succ;
	 

	  public GateWayHourlyCircleResponse(String date_time, String mis_hour, String uniq_msisdn, String
			  session_id, String msisdn, String succ_trans, String abort_trans,
	  String err_succ) { 
		  super();
		  this.date_time = date_time;
		  this.mis_hour = mis_hour;
		  this.uniq_msisdn = uniq_msisdn; 
		  this.session_id = session_id;
		  this.msisdn = msisdn;
	  this.succ_trans = succ_trans;
	  this.abort_trans = abort_trans;
	  this.err_succ = err_succ;
	  
	  
	  }

}
