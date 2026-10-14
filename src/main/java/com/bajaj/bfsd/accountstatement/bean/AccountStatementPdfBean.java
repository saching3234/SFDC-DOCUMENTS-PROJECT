package com.bajaj.bfsd.accountstatement.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Data holder for the Account Statement PDF. In production this bean will be
 * populated from the API response. For now the service fills it with dummy
 * data.
 */
public class AccountStatementPdfBean {

	// Header
	private String companyName;
	private String statementDate;
	private String periodFrom;
	private String periodTo;
	private String loanAccountNumber;
	private String customerId;

	// Applicant / customer block
	private String applicantName;
	private String address;
	private String emailId;
	private String gstStateCode;
	private String gstin;
	private String mobile;
	private String ckycId;
	private String branchCode;
	private String branchAddress;

	// Left summary block
	private String status;
	private String productName;
	private String amountFinance;
	private String disbursalDate;
	private String tenure;
	private String repaymentFrequency;
	private String repaymentMode;
	private String monthsOnBook;
	private String balanceTenure;
	private String interestRate;
	private String rateOfInterest;
	private String amountDisbursedTillDate;
	private String firstEmiDate;
	private String lastEmiDate;
	private String disbursalStatus;
	private String linkedLoanAccountNo;
	private String assetCategory;
	private String assetModel;
	private String assetSrNo;
	private String assetBrand;
	private String assetLoanScheme;
	private String aprPercent;
	private String pendingNoOfEmi;

	// Right (green) summary block
	private String installmentPaid;
	private String principalPaid;
	private String interestPaid;
	private String principalOutstanding;
	private String overduePrincipalAmount;
	private String futurePrincipalAmount;
	private String overdueInterestAmount;
	private String otherOverdues;
	private String additionalInterestPaid;
	private String additionalInterestDue;
	private String bounceChargesPaid;
	private String bounceChargesDue;
	private String totalOutstanding;
	private String installmentOverdue;
	private String unappliedCredits;
	private String lastPaymentAmount;
	private String lastPaymentDate;
	private String manufacturerSubventionAmount;
	private String cpSubventionAmount;
	private String retailerSubventionAmount;
	private String assetValue;
	private String latePaymentChargesPaid;
	private String latePaymentChargesDue;

	// Transactions
	private List<TransactionRow> transactions = new ArrayList<>();

	// Footer notes
	private String serviceTaxCode;
	private String footerGstin;
	private String cin;

	/** Single transaction line of the statement. */
	public static class TransactionRow {
		private String date;
		private String valueDate;
		private String particulars;
		private String debit;
		private String credit;
		private String addlInterestDue;
		private String addlInterestPaid;
		private String bounceChargesDue;
		private String bounceChargesPaid;
		private String latePaymentDue;
		private String latePaymentPaid;

		public TransactionRow() {
		}

		public TransactionRow(String date, String valueDate, String particulars, String debit, String credit,
				String addlInterestDue, String addlInterestPaid, String bounceChargesDue, String bounceChargesPaid,
				String latePaymentDue, String latePaymentPaid) {
			this.date = date;
			this.valueDate = valueDate;
			this.particulars = particulars;
			this.debit = debit;
			this.credit = credit;
			this.addlInterestDue = addlInterestDue;
			this.addlInterestPaid = addlInterestPaid;
			this.bounceChargesDue = bounceChargesDue;
			this.bounceChargesPaid = bounceChargesPaid;
			this.latePaymentDue = latePaymentDue;
			this.latePaymentPaid = latePaymentPaid;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
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

		public String getAddlInterestDue() {
			return addlInterestDue;
		}

		public void setAddlInterestDue(String addlInterestDue) {
			this.addlInterestDue = addlInterestDue;
		}

		public String getAddlInterestPaid() {
			return addlInterestPaid;
		}

		public void setAddlInterestPaid(String addlInterestPaid) {
			this.addlInterestPaid = addlInterestPaid;
		}

		public String getBounceChargesDue() {
			return bounceChargesDue;
		}

		public void setBounceChargesDue(String bounceChargesDue) {
			this.bounceChargesDue = bounceChargesDue;
		}

		public String getBounceChargesPaid() {
			return bounceChargesPaid;
		}

		public void setBounceChargesPaid(String bounceChargesPaid) {
			this.bounceChargesPaid = bounceChargesPaid;
		}

		public String getLatePaymentDue() {
			return latePaymentDue;
		}

		public void setLatePaymentDue(String latePaymentDue) {
			this.latePaymentDue = latePaymentDue;
		}

		public String getLatePaymentPaid() {
			return latePaymentPaid;
		}

		public void setLatePaymentPaid(String latePaymentPaid) {
			this.latePaymentPaid = latePaymentPaid;
		}
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate;
	}

	public String getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}

	public String getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}

	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}

	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGstStateCode() {
		return gstStateCode;
	}

	public void setGstStateCode(String gstStateCode) {
		this.gstStateCode = gstStateCode;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCkycId() {
		return ckycId;
	}

	public void setCkycId(String ckycId) {
		this.ckycId = ckycId;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

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

	public String getAmountFinance() {
		return amountFinance;
	}

	public void setAmountFinance(String amountFinance) {
		this.amountFinance = amountFinance;
	}

	public String getDisbursalDate() {
		return disbursalDate;
	}

	public void setDisbursalDate(String disbursalDate) {
		this.disbursalDate = disbursalDate;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getRepaymentFrequency() {
		return repaymentFrequency;
	}

	public void setRepaymentFrequency(String repaymentFrequency) {
		this.repaymentFrequency = repaymentFrequency;
	}

	public String getRepaymentMode() {
		return repaymentMode;
	}

	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}

	public String getMonthsOnBook() {
		return monthsOnBook;
	}

	public void setMonthsOnBook(String monthsOnBook) {
		this.monthsOnBook = monthsOnBook;
	}

	public String getBalanceTenure() {
		return balanceTenure;
	}

	public void setBalanceTenure(String balanceTenure) {
		this.balanceTenure = balanceTenure;
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

	public String getAmountDisbursedTillDate() {
		return amountDisbursedTillDate;
	}

	public void setAmountDisbursedTillDate(String amountDisbursedTillDate) {
		this.amountDisbursedTillDate = amountDisbursedTillDate;
	}

	public String getFirstEmiDate() {
		return firstEmiDate;
	}

	public void setFirstEmiDate(String firstEmiDate) {
		this.firstEmiDate = firstEmiDate;
	}

	public String getLastEmiDate() {
		return lastEmiDate;
	}

	public void setLastEmiDate(String lastEmiDate) {
		this.lastEmiDate = lastEmiDate;
	}

	public String getDisbursalStatus() {
		return disbursalStatus;
	}

	public void setDisbursalStatus(String disbursalStatus) {
		this.disbursalStatus = disbursalStatus;
	}

	public String getLinkedLoanAccountNo() {
		return linkedLoanAccountNo;
	}

	public void setLinkedLoanAccountNo(String linkedLoanAccountNo) {
		this.linkedLoanAccountNo = linkedLoanAccountNo;
	}

	public String getAssetCategory() {
		return assetCategory;
	}

	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}

	public String getAssetModel() {
		return assetModel;
	}

	public void setAssetModel(String assetModel) {
		this.assetModel = assetModel;
	}

	public String getAssetSrNo() {
		return assetSrNo;
	}

	public void setAssetSrNo(String assetSrNo) {
		this.assetSrNo = assetSrNo;
	}

	public String getAssetBrand() {
		return assetBrand;
	}

	public void setAssetBrand(String assetBrand) {
		this.assetBrand = assetBrand;
	}

	public String getAssetLoanScheme() {
		return assetLoanScheme;
	}

	public void setAssetLoanScheme(String assetLoanScheme) {
		this.assetLoanScheme = assetLoanScheme;
	}

	public String getAprPercent() {
		return aprPercent;
	}

	public void setAprPercent(String aprPercent) {
		this.aprPercent = aprPercent;
	}

	public String getPendingNoOfEmi() {
		return pendingNoOfEmi;
	}

	public void setPendingNoOfEmi(String pendingNoOfEmi) {
		this.pendingNoOfEmi = pendingNoOfEmi;
	}

	public String getInstallmentPaid() {
		return installmentPaid;
	}

	public void setInstallmentPaid(String installmentPaid) {
		this.installmentPaid = installmentPaid;
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

	public String getFuturePrincipalAmount() {
		return futurePrincipalAmount;
	}

	public void setFuturePrincipalAmount(String futurePrincipalAmount) {
		this.futurePrincipalAmount = futurePrincipalAmount;
	}

	public String getOverdueInterestAmount() {
		return overdueInterestAmount;
	}

	public void setOverdueInterestAmount(String overdueInterestAmount) {
		this.overdueInterestAmount = overdueInterestAmount;
	}

	public String getOtherOverdues() {
		return otherOverdues;
	}

	public void setOtherOverdues(String otherOverdues) {
		this.otherOverdues = otherOverdues;
	}

	public String getAdditionalInterestPaid() {
		return additionalInterestPaid;
	}

	public void setAdditionalInterestPaid(String additionalInterestPaid) {
		this.additionalInterestPaid = additionalInterestPaid;
	}

	public String getAdditionalInterestDue() {
		return additionalInterestDue;
	}

	public void setAdditionalInterestDue(String additionalInterestDue) {
		this.additionalInterestDue = additionalInterestDue;
	}

	public String getBounceChargesPaid() {
		return bounceChargesPaid;
	}

	public void setBounceChargesPaid(String bounceChargesPaid) {
		this.bounceChargesPaid = bounceChargesPaid;
	}

	public String getBounceChargesDue() {
		return bounceChargesDue;
	}

	public void setBounceChargesDue(String bounceChargesDue) {
		this.bounceChargesDue = bounceChargesDue;
	}

	public String getTotalOutstanding() {
		return totalOutstanding;
	}

	public void setTotalOutstanding(String totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
	}

	public String getInstallmentOverdue() {
		return installmentOverdue;
	}

	public void setInstallmentOverdue(String installmentOverdue) {
		this.installmentOverdue = installmentOverdue;
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

	public String getManufacturerSubventionAmount() {
		return manufacturerSubventionAmount;
	}

	public void setManufacturerSubventionAmount(String manufacturerSubventionAmount) {
		this.manufacturerSubventionAmount = manufacturerSubventionAmount;
	}

	public String getCpSubventionAmount() {
		return cpSubventionAmount;
	}

	public void setCpSubventionAmount(String cpSubventionAmount) {
		this.cpSubventionAmount = cpSubventionAmount;
	}

	public String getRetailerSubventionAmount() {
		return retailerSubventionAmount;
	}

	public void setRetailerSubventionAmount(String retailerSubventionAmount) {
		this.retailerSubventionAmount = retailerSubventionAmount;
	}

	public String getAssetValue() {
		return assetValue;
	}

	public void setAssetValue(String assetValue) {
		this.assetValue = assetValue;
	}

	public String getLatePaymentChargesPaid() {
		return latePaymentChargesPaid;
	}

	public void setLatePaymentChargesPaid(String latePaymentChargesPaid) {
		this.latePaymentChargesPaid = latePaymentChargesPaid;
	}

	public String getLatePaymentChargesDue() {
		return latePaymentChargesDue;
	}

	public void setLatePaymentChargesDue(String latePaymentChargesDue) {
		this.latePaymentChargesDue = latePaymentChargesDue;
	}

	public List<TransactionRow> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionRow> transactions) {
		this.transactions = transactions;
	}

	public String getServiceTaxCode() {
		return serviceTaxCode;
	}

	public void setServiceTaxCode(String serviceTaxCode) {
		this.serviceTaxCode = serviceTaxCode;
	}

	public String getFooterGstin() {
		return footerGstin;
	}

	public void setFooterGstin(String footerGstin) {
		this.footerGstin = footerGstin;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}
}
