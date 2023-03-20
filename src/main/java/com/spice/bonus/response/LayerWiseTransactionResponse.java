package com.spice.bonus.response;

import lombok.Data;

@Data
public class LayerWiseTransactionResponse {
	private String concatReqtxt;
	private String uniqueMsisdns;
	private String totalTransactions;
	public LayerWiseTransactionResponse(String concatReqtxt, String uniqueMsisdns, String totalTransactions) {
		super();
		this.concatReqtxt = concatReqtxt;
		this.uniqueMsisdns = uniqueMsisdns;
		this.totalTransactions = totalTransactions;
	}
}
