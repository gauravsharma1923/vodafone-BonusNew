package com.spice.bonus.response;

import lombok.Data;

@Data
public class TransactionResponse {
	private String data;
	private String dt;
	private String transaction;
	//private String transaction[] = new String[30];
	public TransactionResponse(String data, String dt, String transaction) {
		super();
		this.data = data;
		this.dt = dt;
		this.transaction = transaction;
		/*int j = 0;
		for (String i: transaction)
		{
			this.transaction[j++] = i;
		}*/
	}
}
