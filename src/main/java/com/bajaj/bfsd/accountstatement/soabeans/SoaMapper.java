package com.bajaj.bfsd.accountstatement.soabeans;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bajaj.bfsd.accountstatement.bean.AccountStatementPdfBean;
import com.bajaj.bfsd.accountstatement.bean.AccountStatementPdfBean.TransactionRow;

/**
 * Maps the SOA API response ({@link SoaResponse}) into the template-friendly
 * {@link AccountStatementPdfBean} that drives the FreeMarker / iText output.
 */
@Component
public class SoaMapper {

	private static final String COMPANY_NAME = "SMFG India Credit Co. Ltd.";
	private static final String SERVICE_TAX_CODE = "AACD1707CST001";
	private static final String FOOTER_GSTIN = "33AAACD1707C1ZI";
	private static final String CIN = "U65191TN1994PLC079235";

	/**
	 * Converts a parsed SOA response into the PDF template bean.
	 *
	 * @param response the SOA API response.
	 * @return a fully populated {@link AccountStatementPdfBean}.
	 */
	public AccountStatementPdfBean toPdfBean(SoaResponse response) {
		AccountStatementPdfBean bean = new AccountStatementPdfBean();
		bean.setCompanyName(COMPANY_NAME);
		bean.setServiceTaxCode(SERVICE_TAX_CODE);
		bean.setFooterGstin(FOOTER_GSTIN);
		bean.setCin(CIN);

		if (response == null || response.getSoaResultSet() == null) {
			return bean;
		}

		String bussDate = null;
		SoaCustDetail applicant = null;
		SoaSummary summary = null;
		List<SoaParticular> particulars = null;

		// Walk through the result-set; each entry carries one section.
		for (SoaResultSet rs : response.getSoaResultSet()) {
			if (rs.getBussDate() != null) {
				bussDate = rs.getBussDate();
			}
			if (rs.getSoaCustDetail() != null && applicant == null) {
				applicant = pickApplicant(rs.getSoaCustDetail());
			}
			if (rs.getSoaSummary() != null && !rs.getSoaSummary().isEmpty()) {
				summary = rs.getSoaSummary().get(0);
			}
			if (rs.getSoaParticulars() != null) {
				particulars = rs.getSoaParticulars();
			}
		}

		bean.setStatementDate(bussDate);
		mapCustomer(bean, applicant);
		mapSummary(bean, summary);
		mapParticulars(bean, particulars);
		mapPeriod(bean, bussDate, particulars);
		return bean;
	}

	/** Returns the APPLICANT record (falls back to the first entry). */
	private SoaCustDetail pickApplicant(List<SoaCustDetail> details) {
		for (SoaCustDetail d : details) {
			if ("APPLICANT".equalsIgnoreCase(d.getCustomerType())) {
				return d;
			}
		}
		return details.isEmpty() ? null : details.get(0);
	}

	private void mapCustomer(AccountStatementPdfBean bean, SoaCustDetail cust) {
		if (cust == null) {
			return;
		}
		bean.setApplicantName(cust.getCustomerName());
		bean.setCustomerId(cust.getCustomerId());
		bean.setLoanAccountNumber(cust.getLan());
		bean.setAddress(cust.getCustAddress());
		bean.setEmailId(cust.getCustEmailId());
		bean.setMobile(cust.getCustMobileNo());
		bean.setGstStateCode(cust.getCustomerGstCode());
		bean.setGstin(cust.getCustomerGstinNo());
		bean.setBranchCode(cust.getBranchCode());
		bean.setBranchAddress(join(cust.getBranchAddress1(), cust.getBranchAddress2()));
		bean.setCkycId("NA");
	}

	private void mapSummary(AccountStatementPdfBean bean, SoaSummary s) {
		if (s == null) {
			return;
		}
		bean.setStatus(s.getStatus());
		bean.setProductName(s.getProductName());
		bean.setAmountFinance(money(s.getTotalSanctionedAmount()));
		bean.setDisbursalDate(s.getLimitStartDate());
		bean.setTenure(s.getTenure());
		bean.setRepaymentFrequency(s.getRepaymentFrequency());
		bean.setRepaymentMode(s.getRepaymentMode());
		bean.setMonthsOnBook(s.getMob());
		bean.setBalanceTenure(s.getBalanceTenure());
		bean.setInterestRate(s.getInterestRate());
		bean.setRateOfInterest(s.getRateOfInterest());
		bean.setAmountDisbursedTillDate(money(s.getUtilizedLimit()));
		bean.setFirstEmiDate(s.getLimitStartDate());
		bean.setLastEmiDate(s.getLimitEndDate());
		bean.setDisbursalStatus(s.getStatus());
		bean.setLinkedLoanAccountNo(s.getOpenLans());
		bean.setAssetCategory("-");
		bean.setAssetModel("-");
		bean.setAssetSrNo("-");
		bean.setAssetBrand("-");
		bean.setAssetLoanScheme("-");
		bean.setAprPercent("-");
		bean.setPendingNoOfEmi(s.getTenure());

		bean.setInstallmentPaid(money("0"));
		bean.setPrincipalPaid(money(s.getPrincipalPaid()));
		bean.setInterestPaid(money(s.getInterestPaid()));
		bean.setPrincipalOutstanding(money(s.getPrincipalOutstanding()));
		bean.setOverduePrincipalAmount(money(s.getOverduePrincipalAmount()));
		bean.setFuturePrincipalAmount(money(s.getFuturePrincipalAmt()));
		bean.setOverdueInterestAmount(money(s.getOverdueInterestAmount()));
		bean.setOtherOverdues(money(s.getOtherOverdues()));
		bean.setAdditionalInterestPaid(money(s.getAddInterestPaid()));
		bean.setAdditionalInterestDue(money(s.getAddInterestDue()));
		bean.setBounceChargesPaid(money(s.getBounceChargePaid()));
		bean.setBounceChargesDue(money(s.getBounceChargeDue()));
		bean.setTotalOutstanding(money(s.getTotalOutstanding()));
		bean.setInstallmentOverdue(money("0"));
		bean.setUnappliedCredits(money(s.getUnappliedCredits()));
		bean.setLastPaymentAmount(money(s.getLastPaymentAmount()));
		bean.setLastPaymentDate(s.getLastPaymentDate());
		bean.setManufacturerSubventionAmount(money("0"));
		bean.setCpSubventionAmount(money("0"));
		bean.setRetailerSubventionAmount(money("0"));
		bean.setAssetValue(money("0"));
		bean.setLatePaymentChargesPaid(money("0"));
		bean.setLatePaymentChargesDue(money("0"));
	}

	private void mapParticulars(AccountStatementPdfBean bean, List<SoaParticular> particulars) {
		if (particulars == null) {
			return;
		}
		for (SoaParticular p : particulars) {
			bean.getTransactions().add(new TransactionRow(
					p.getTxnDate(),
					p.getValueDate(),
					p.getParticulars(),
					money(p.getDebit()),
					money(p.getCredit()),
					money(p.getAdditionalInterestDue()),
					money(p.getAdditionalInterestPaid()),
					money(p.getBounceChargeDue()),
					money(p.getBounceChargePaid()),
					money("0"),
					money("0")));
		}
	}

	/** Derives the statement period from the transaction dates. */
	private void mapPeriod(AccountStatementPdfBean bean, String bussDate, List<SoaParticular> particulars) {
		if (particulars != null && !particulars.isEmpty()) {
			bean.setPeriodFrom(particulars.get(0).getTxnDate());
			bean.setPeriodTo(bussDate != null ? bussDate : particulars.get(particulars.size() - 1).getTxnDate());
		} else {
			bean.setPeriodFrom(bussDate);
			bean.setPeriodTo(bussDate);
		}
	}

	/* ===================== helpers ===================== */

	/** Formats a numeric string as {@code #,##0.00}; passes through non-numeric placeholders like "-". */
	private String money(String value) {
		if (value == null || value.trim().isEmpty()) {
			return "0.00";
		}
		String v = value.trim();
		if ("-".equals(v) || "NA".equalsIgnoreCase(v)) {
			return v;
		}
		try {
			double d = Double.parseDouble(v.replace(",", ""));
			return String.format("%,.2f", d);
		} catch (NumberFormatException e) {
			return v;
		}
	}

	private String join(String a, String b) {
		if (a == null) {
			return b == null ? "" : b;
		}
		if (b == null) {
			return a;
		}
		return a + ", " + b;
	}
}
