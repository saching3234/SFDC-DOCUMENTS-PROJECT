package com.bajaj.bfsd.accountstatement.soabeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Summary section ({@code SOA_SUMMARY}). Numeric values are kept as String
 * because the API mixes numbers and placeholder strings such as {@code "-"}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SoaSummary {

	@JsonProperty("STATUS")
	private String status;

	@JsonProperty("PRODUCT_NAME")
	private String productName;

	@JsonProperty("TOTAL_SANCTIONED_AMOUNT")
	private String totalSanctionedAmount;

	@JsonProperty("UTILIZED_LIMIT")
	private String utilizedLimit;

	@JsonProperty("LIMIT_START_DATE")
	private String limitStartDate;

	@JsonProperty("LIMIT_END_DATE")
	private String limitEndDate;

	@JsonProperty("TENURE")
	private String tenure;

	@JsonProperty("BALANCE_TENURE")
	private String balanceTenure;

	@JsonProperty("MOB")
	private String mob;

	@JsonProperty("REPAYMENT_MODE")
	private String repaymentMode;

	@JsonProperty("REPAYMENT_FREQUENCY")
	private String repaymentFrequency;

	@JsonProperty("INTEREST_RATE")
	private String interestRate;

	@JsonProperty("RATE_OF_INTEREST")
	private String rateOfInterest;

	@JsonProperty("OPEN_LANS")
	private String openLans;

	@JsonProperty("PRINCIPAL_PAID")
	private String principalPaid;

	@JsonProperty("INTEREST_PAID")
	private String interestPaid;

	@JsonProperty("PRINCIPAL_OUTSTANDING")
	private String principalOutstanding;

	@JsonProperty("OVERDUE_PRINCIPAL_AMOUNT")
	private String overduePrincipalAmount;

	@JsonProperty("OVERDUE_INTEREST_AMOUNT")
	private String overdueInterestAmount;

	@JsonProperty("FUTURE_PRINCIPAL_AMT")
	private String futurePrincipalAmt;

	@JsonProperty("OTHER_OVERDUES")
	private String otherOverdues;

	@JsonProperty("ADD_INTREST_PAID")
	private String addInterestPaid;

	@JsonProperty("ADD_INTREST_DUE")
	private String addInterestDue;

	@JsonProperty("BOUNCE_CHARGE_PAID")
	private String bounceChargePaid;

	@JsonProperty("BOUNCE_CHARGE_DUE")
	private String bounceChargeDue;

	@JsonProperty("TOTAL_OUTSTANDING")
	private String totalOutstanding;

	@JsonProperty("UNAPPLIED_CREDITS")
	private String unappliedCredits;

	@JsonProperty("LAST_PAYMENT_AMOUNT")
	private String lastPaymentAmount;

	@JsonProperty("LAST_PAYMENT_DATE")
	private String lastPaymentDate;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTotalSanctionedAmount() {
		return totalSanctionedAmount;
	}

	public void setTotalSanctionedAmount(String totalSanctionedAmount) {
		this.totalSanctionedAmount = totalSanctionedAmount;
	}

	public String getUtilizedLimit() {
		return utilizedLimit;
	}

	public void setUtilizedLimit(String utilizedLimit) {
		this.utilizedLimit = utilizedLimit;
	}

	public String getLimitStartDate() {
		return limitStartDate;
	}

	public void setLimitStartDate(String limitStartDate) {
		this.limitStartDate = limitStartDate;
	}

	public String getLimitEndDate() {
		return limitEndDate;
	}

	public void setLimitEndDate(String limitEndDate) {
		this.limitEndDate = limitEndDate;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getBalanceTenure() {
		return balanceTenure;
	}

	public void setBalanceTenure(String balanceTenure) {
		this.balanceTenure = balanceTenure;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getRepaymentMode() {
		return repaymentMode;
	}

	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}

	public String getRepaymentFrequency() {
		return repaymentFrequency;
	}

	public void setRepaymentFrequency(String repaymentFrequency) {
		this.repaymentFrequency = repaymentFrequency;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(String rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public String getOpenLans() {
		return openLans;
	}

	public void setOpenLans(String openLans) {
		this.openLans = openLans;
	}

	public String getPrincipalPaid() {
		return principalPaid;
	}

	public void setPrincipalPaid(String principalPaid) {
		this.principalPaid = principalPaid;
	}

	public String getInterestPaid() {
		return interestPaid;
	}

	public void setInterestPaid(String interestPaid) {
		this.interestPaid = interestPaid;
	}

	public String getPrincipalOutstanding() {
		return principalOutstanding;
	}

	public void setPrincipalOutstanding(String principalOutstanding) {
		this.principalOutstanding = principalOutstanding;
	}

	public String getOverduePrincipalAmount() {
		return overduePrincipalAmount;
	}

	public void setOverduePrincipalAmount(String overduePrincipalAmount) {
		this.overduePrincipalAmount = overduePrincipalAmount;
	}

	public String getOverdueInterestAmount() {
		return overdueInterestAmount;
	}

	public void setOverdueInterestAmount(String overdueInterestAmount) {
		this.overdueInterestAmount = overdueInterestAmount;
	}

	public String getFuturePrincipalAmt() {
		return futurePrincipalAmt;
	}

	public void setFuturePrincipalAmt(String futurePrincipalAmt) {
		this.futurePrincipalAmt = futurePrincipalAmt;
	}

	public String getOtherOverdues() {
		return otherOverdues;
	}

	public void setOtherOverdues(String otherOverdues) {
		this.otherOverdues = otherOverdues;
	}

	public String getAddInterestPaid() {
		return addInterestPaid;
	}

	public void setAddInterestPaid(String addInterestPaid) {
		this.addInterestPaid = addInterestPaid;
	}

	public String getAddInterestDue() {
		return addInterestDue;
	}

	public void setAddInterestDue(String addInterestDue) {
		this.addInterestDue = addInterestDue;
	}

	public String getBounceChargePaid() {
		return bounceChargePaid;
	}

	public void setBounceChargePaid(String bounceChargePaid) {
		this.bounceChargePaid = bounceChargePaid;
	}

	public String getBounceChargeDue() {
		return bounceChargeDue;
	}

	public void setBounceChargeDue(String bounceChargeDue) {
		this.bounceChargeDue = bounceChargeDue;
	}

	public String getTotalOutstanding() {
		return totalOutstanding;
	}

	public void setTotalOutstanding(String totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
	}

	public String getUnappliedCredits() {
		return unappliedCredits;
	}

	public void setUnappliedCredits(String unappliedCredits) {
		this.unappliedCredits = unappliedCredits;
	}

	public String getLastPaymentAmount() {
		return lastPaymentAmount;
	}

	public void setLastPaymentAmount(String lastPaymentAmount) {
		this.lastPaymentAmount = lastPaymentAmount;
	}

	public String getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(String lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
}
