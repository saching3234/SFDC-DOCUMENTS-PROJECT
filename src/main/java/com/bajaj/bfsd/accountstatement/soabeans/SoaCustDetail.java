package com.bajaj.bfsd.accountstatement.soabeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Customer / applicant details ({@code SOA_CUST_DTL}).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SoaCustDetail {

	@JsonProperty("CUSTOMER_NAME")
	private String customerName;

	@JsonProperty("CUSTOMER_ID")
	private String customerId;

	@JsonProperty("CUSTOMER_TYPE")
	private String customerType;

	@JsonProperty("LAN")
	private String lan;

	@JsonProperty("CUST_ADDRESS")
	private String custAddress;

	@JsonProperty("CUST_EMAIL_ID")
	private String custEmailId;

	@JsonProperty("CUST_MOBILE_NO")
	private String custMobileNo;

	@JsonProperty("CUSTOMER_GST_CODE")
	private String customerGstCode;

	@JsonProperty("CUSTOMER_GSTIN_NO")
	private String customerGstinNo;

	@JsonProperty("BRANCH_CODE")
	private String branchCode;

	@JsonProperty("BRANCH_ADDRESS1")
	private String branchAddress1;

	@JsonProperty("BRANCH_ADDRESS2")
	private String branchAddress2;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getLan() {
		return lan;
	}

	public void setLan(String lan) {
		this.lan = lan;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustEmailId() {
		return custEmailId;
	}

	public void setCustEmailId(String custEmailId) {
		this.custEmailId = custEmailId;
	}

	public String getCustMobileNo() {
		return custMobileNo;
	}

	public void setCustMobileNo(String custMobileNo) {
		this.custMobileNo = custMobileNo;
	}

	public String getCustomerGstCode() {
		return customerGstCode;
	}

	public void setCustomerGstCode(String customerGstCode) {
		this.customerGstCode = customerGstCode;
	}

	public String getCustomerGstinNo() {
		return customerGstinNo;
	}

	public void setCustomerGstinNo(String customerGstinNo) {
		this.customerGstinNo = customerGstinNo;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchAddress1() {
		return branchAddress1;
	}

	public void setBranchAddress1(String branchAddress1) {
		this.branchAddress1 = branchAddress1;
	}

	public String getBranchAddress2() {
		return branchAddress2;
	}

	public void setBranchAddress2(String branchAddress2) {
		this.branchAddress2 = branchAddress2;
	}
}
