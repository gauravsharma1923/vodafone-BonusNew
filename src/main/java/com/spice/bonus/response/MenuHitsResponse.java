package com.spice.bonus.response;

import lombok.Data;

@Data
public class MenuHitsResponse {
	
	  private String MIS_DATE;
	  private String Language;
	  private String circle;
	  
	  private String short_code;
	  private String c_sess;
	  private String c_tot_hits;
	  
	  private String c_uniq_hits;
	  private String c_mtd_hits;
	  
	
	
	  public MenuHitsResponse(String MIS_DATE, String Language, String
			  circle, String short_code, String c_sess, String c_tot_hits,
	  String c_uniq_hits, String c_mtd_hits) { 
		  super();
		  this.MIS_DATE = MIS_DATE;
		  this.Language = Language; 
		  this.circle = circle;
		  this.short_code = short_code;
	  this.c_sess = c_sess;
	  this.c_tot_hits = c_tot_hits;
	  this.c_uniq_hits = c_uniq_hits;
	  this.c_mtd_hits = c_mtd_hits;
	  
	  }
	 

}
