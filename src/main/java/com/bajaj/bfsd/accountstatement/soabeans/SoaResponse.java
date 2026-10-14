package com.bajaj.bfsd.accountstatement.soabeans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Root response bean for the SOA (Statement of Account) API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SoaResponse {

	@JsonProperty("STATUS")
	private String status;

	@JsonProperty("MESSAGE")
	private String message;

	@JsonProperty("SOA_RESULTSET")
	private List<SoaResultSet> soaResultSet;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<SoaResultSet> getSoaResultSet() {
		return soaResultSet;
	}

	public void setSoaResultSet(List<SoaResultSet> soaResultSet) {
		this.soaResultSet = soaResultSet;
	}
}
