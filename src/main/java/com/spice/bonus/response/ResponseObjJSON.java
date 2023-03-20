package com.spice.bonus.response;

import org.codehaus.jettison.json.JSONArray;

import lombok.Data;

@Data
public class ResponseObjJSON {
	private int statusCode;
	private String description;
	private String status;
	private Object object;
	
	
	public ResponseObjJSON(JSONArray object, String status, String description,int statusCode) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.description = description;
		this.object = object;
	}
	
	public ResponseObjJSON(JSONArray object) {
		super();
		this.statusCode = 100;
		this.status = "Success";
		this.description = "Success";
		this.object = object;
	}
	
}
