package com.bajaj.bfsd.accountstatement.soabeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A single transaction line ({@code SOA_PARTICULARS}).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SoaParticular {

	@JsonProperty("SI_NO")
	private String siNo;

	@JsonProperty("TXNDATE")
	private String txnDate;

	@JsonProperty("VALUEDATE")
	private String valueDate;

	@JsonProperty("PARTICULARS")
	private String particulars;

	@JsonProperty("DEBIT")
	private String debit;

	@JsonProperty("CREDIT")
	private String credit;

	@JsonProperty("ADDTIONAL_INTREST_DUE")
	private String additionalInterestDue;

	@JsonProperty("ADDTIONAL_INTREST_PAID")
	private String additionalInterestPaid;

	@JsonProperty("BOUNCE_CHARGE_DUE")
	private String bounceChargeDue;

	@JsonProperty("BOUNCE_CHARGE_PAID")
	private String bounceChargePaid;

	public String getSiNo() {
		return siNo;
	}

	public void setSiNo(String siNo) {
		this.siNo = siNo;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getDebit() {
		return debit;
	}

	public void setDebit(String debit) {
		this.debit = debit;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getAdditionalInterestDue() {
		return additionalInterestDue;
	}

	public void setAdditionalInterestDue(String additionalInterestDue) {
		this.additionalInterestDue = additionalInterestDue;
	}

	public String getAdditionalInterestPaid() {
		return additionalInterestPaid;
	}

	public void setAdditionalInterestPaid(String additionalInterestPaid) {
		this.additionalInterestPaid = additionalInterestPaid;
	}

	public String getBounceChargeDue() {
		return bounceChargeDue;
	}

	public void setBounceChargeDue(String bounceChargeDue) {
		this.bounceChargeDue = bounceChargeDue;
	}

	public String getBounceChargePaid() {
		return bounceChargePaid;
	}

	public void setBounceChargePaid(String bounceChargePaid) {
		this.bounceChargePaid = bounceChargePaid;
	}
}
