package com.spice.bonus.response;

import lombok.Data;

@Data
public class View444InResponse {
	private String circleId;
	private String rankOrder;
	private String offerName;
	private String bonusName;
	private String value;
	private String amount;
	private String threshHold;
	private String debitEventId;
	private String creditEventId;
	private String validity;
	private String activationMode;
	private String systmCreatDate;
	private String systmUpdatDate;
	private String effectiveDate;
	private String expDate;
	private String mdmId;
	private String brand;
	private String status;
	public View444InResponse(String circleId, String rankOrder, String offerName, String bonusName, String value,
			String amount, String threshHold, String debitEventId, String creditEventId, String validity,
			String activationMode, String systmCreatDate, String systmUpdatDate, String effectiveDate, String expDate,
			String mdmId, String brand, String status) {
		super();
		this.circleId = circleId;
		this.rankOrder = rankOrder;
		this.offerName = offerName;
		this.bonusName = bonusName;
		this.value = value;
		this.amount = amount;
		this.threshHold = threshHold;
		this.debitEventId = debitEventId;
		this.creditEventId = creditEventId;
		this.validity = validity;
		this.activationMode = activationMode;
		this.systmCreatDate = systmCreatDate;
		this.systmUpdatDate = systmUpdatDate;
		this.effectiveDate = effectiveDate;
		this.expDate = expDate;
		this.mdmId = mdmId;
		this.brand = brand;
		this.status = status;
	}
}
