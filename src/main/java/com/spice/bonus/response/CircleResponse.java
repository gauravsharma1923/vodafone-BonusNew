package com.spice.bonus.response;

import lombok.Data;

@Data
public class CircleResponse {
	
	  
	
	private String decription;
	private String circle_name;
	private String circle_id;
	
	
	  
	 
	public CircleResponse(String decription, String circle_name, String circle_id) {
		super();
		this.decription = decription;
		this.circle_name = circle_name;
		this.circle_id = circle_id;
		
		
		
	}
}
