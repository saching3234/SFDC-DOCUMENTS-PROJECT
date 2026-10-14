package com.bajaj.bfsd.accountstatement.soabeans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A single entry in {@code SOA_RESULTSET}. The API returns several of these,
 * each carrying a different section (customer details, summary or
 * particulars), so all three lists are optional.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SoaResultSet {

	@JsonProperty("BUSS_DATE")
	private String bussDate;

	@JsonProperty("SOA_CUST_DTL")
	private List<SoaCustDetail> soaCustDetail;

	@JsonProperty("SOA_SUMMARY")
	private List<SoaSummary> soaSummary;

	@JsonProperty("SOA_PARTICULARS")
	private List<SoaParticular> soaParticulars;

	public String getBussDate() {
		return bussDate;
	}

	public void setBussDate(String bussDate) {
		this.bussDate = bussDate;
	}

	public List<SoaCustDetail> getSoaCustDetail() {
		return soaCustDetail;
	}

	public void setSoaCustDetail(List<SoaCustDetail> soaCustDetail) {
		this.soaCustDetail = soaCustDetail;
	}

	public List<SoaSummary> getSoaSummary() {
		return soaSummary;
	}

	public void setSoaSummary(List<SoaSummary> soaSummary) {
		this.soaSummary = soaSummary;
	}

	public List<SoaParticular> getSoaParticulars() {
		return soaParticulars;
	}

	public void setSoaParticulars(List<SoaParticular> soaParticulars) {
		this.soaParticulars = soaParticulars;
	}
}
