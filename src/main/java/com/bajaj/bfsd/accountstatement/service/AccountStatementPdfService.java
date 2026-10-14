package com.bajaj.bfsd.accountstatement.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bajaj.bfsd.accountstatement.bean.AccountStatementPdfBean;
import com.bajaj.bfsd.accountstatement.bean.AccountStatementPdfBean.TransactionRow;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Generates the Account Statement (SOA) PDF using iText. The data is supplied
 * via {@link AccountStatementPdfBean}. For now {@link #buildDummyData()} is used
 * to populate the bean, but this can later be replaced by data coming from the
 * API.
 */
@Service
public class AccountStatementPdfService {

	// Brand colours
	private static final BaseColor HEADER_GREEN = new BaseColor(0, 102, 51);
	private static final BaseColor LIGHT_GREEN = new BaseColor(210, 233, 210);
	private static final BaseColor GREY = new BaseColor(230, 230, 230);
	private static final BaseColor YELLOW = new BaseColor(214, 223, 40);

	// Fonts
	private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	private static final Font BOLD_FONT = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
	private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
	private static final Font SMALL_FONT = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);
	private static final Font WHITE_BOLD = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.WHITE);
	private static final Font TABLE_HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);

	/**
	 * Generates the PDF for the given data and returns it as a byte array.
	 *
	 * @param data statement data; if {@code null} dummy data is used.
	 * @return PDF content as bytes.
	 */
	public byte[] generateAccountStatementPdf(AccountStatementPdfBean data) {
		if (data == null) {
			data = buildDummyData();
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4, 36, 36, 36, 36);
		try {
			PdfWriter.getInstance(document, baos);
			document.open();

			// ---- Page 1 : summary ----
			addPageHeader(document, data);
			addAccountBar(document, data);
			addApplicantBlock(document, data);
			addBranchBlock(document, data);
			addSummaryBlock(document, data);
			addPageFooter(document, "Page 1 of 3");

			// ---- Page 2 : transactions ----
			document.newPage();
			addPageHeader(document, data);
			addAccountBar(document, data);
			addTransactionTable(document, data);
			addPageFooter(document, "Page 2 of 3");

			// ---- Page 3 : notes ----
			document.newPage();
			addPageHeader(document, data);
			addAccountBar(document, data);
			addNotesBlock(document, data);
			addPageFooter(document, "Page 3 of 3");

			document.close();
		} catch (Exception e) {
			throw new IllegalStateException("Error generating Account Statement PDF", e);
		}
		return baos.toByteArray();
	}

	/* ===================== Page building blocks ===================== */

	private void addPageHeader(Document document, AccountStatementPdfBean data) throws Exception {
		PdfPTable header = new PdfPTable(2);
		header.setWidthPercentage(100);
		header.setWidths(new float[] { 60, 40 });

		PdfPCell logo = new PdfPCell(new Phrase("SMFG\nIndia Credit",
				new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, HEADER_GREEN)));
		logo.setBorder(Rectangle.NO_BORDER);
		header.addCell(logo);

		PdfPCell date = new PdfPCell(new Phrase(data.getStatementDate(), NORMAL_FONT));
		date.setBorder(Rectangle.NO_BORDER);
		date.setHorizontalAlignment(Element.ALIGN_RIGHT);
		header.addCell(date);
		document.add(header);

		Paragraph company = new Paragraph(data.getCompanyName(), TITLE_FONT);
		company.setAlignment(Element.ALIGN_CENTER);
		company.setSpacingBefore(6);
		company.setSpacingAfter(10);
		document.add(company);

		PdfPTable period = new PdfPTable(1);
		period.setWidthPercentage(100);
		PdfPCell pc = new PdfPCell();
		pc.setBorder(Rectangle.NO_BORDER);
		Phrase phrase = new Phrase();
		phrase.add(new Chunk("Account Statement for period : ", NORMAL_FONT));
		phrase.add(new Chunk("   " + data.getPeriodFrom() + "  -  " + data.getPeriodTo(), BOLD_FONT));
		pc.addElement(phrase);
		period.addCell(pc);
		document.add(period);
	}

	private void addAccountBar(Document document, AccountStatementPdfBean data) throws Exception {
		PdfPTable bar = new PdfPTable(2);
		bar.setWidthPercentage(100);
		bar.setSpacingBefore(4);
		bar.setSpacingAfter(6);

		PdfPCell left = new PdfPCell(new Phrase("Loan Account Number      " + safe(data.getLoanAccountNumber()),
				WHITE_BOLD));
		left.setBackgroundColor(HEADER_GREEN);
		left.setPadding(6);
		left.setBorder(Rectangle.NO_BORDER);
		bar.addCell(left);

		PdfPCell right = new PdfPCell(new Phrase("Customer ID  " + safe(data.getCustomerId()), WHITE_BOLD));
		right.setBackgroundColor(HEADER_GREEN);
		right.setPadding(6);
		right.setHorizontalAlignment(Element.ALIGN_RIGHT);
		right.setBorder(Rectangle.NO_BORDER);
		bar.addCell(right);
		document.add(bar);
	}

	private void addApplicantBlock(Document document, AccountStatementPdfBean data) throws Exception {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 55, 45 });

		// left cell : name, address, contact
		PdfPCell left = new PdfPCell();
		left.setPadding(6);
		left.addElement(new Paragraph(safe(data.getApplicantName()), BOLD_FONT));
		left.addElement(new Paragraph(safe(data.getAddress()), NORMAL_FONT));
		left.addElement(new Paragraph(" ", SMALL_FONT));
		left.addElement(labelValue("Email ID : ", data.getEmailId()));
		left.addElement(labelValue("GST State Code : ", data.getGstStateCode()));
		left.addElement(labelValue("GSTIN : ", data.getGstin()));
		left.addElement(labelValue("Mobile : ", data.getMobile()));
		table.addCell(left);

		// right cell : applicant / ckyc
		PdfPCell right = new PdfPCell();
		right.setPadding(6);
		right.addElement(labelValue("APPLICANT        : ", data.getApplicantName()));
		right.addElement(labelValue("CKYC ID          : ", data.getCkycId()));
		table.addCell(right);
		document.add(table);
	}

	private void addBranchBlock(Document document, AccountStatementPdfBean data) throws Exception {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell();
		cell.setPadding(6);
		cell.addElement(labelValue("Branch Code : ", data.getBranchCode()));
		cell.addElement(labelValue("Branch Address : ", data.getBranchAddress()));
		table.addCell(cell);
		table.setSpacingAfter(8);
		document.add(table);
	}

	private void addSummaryBlock(Document document, AccountStatementPdfBean data) throws Exception {
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 28, 22, 30, 20 });

		// Left grey block (label / value) and right green block (label / value)
		String[][] left = {
				{ "Status", data.getStatus() },
				{ "Product Name", data.getProductName() },
				{ "Amount Finance (Rs.)", data.getAmountFinance() },
				{ "Disbursal Date", data.getDisbursalDate() },
				{ "Tenure", data.getTenure() },
				{ "Repayment Frequency", data.getRepaymentFrequency() },
				{ "Repayment Mode", data.getRepaymentMode() },
				{ "Months on Book (MOB)", data.getMonthsOnBook() },
				{ "Balance Tenure", data.getBalanceTenure() },
				{ "Interest Rate", data.getInterestRate() },
				{ "Rate of Interest", data.getRateOfInterest() },
				{ "Amount Disbursed till date (Rs.)", data.getAmountDisbursedTillDate() },
				{ "First EMI Date", data.getFirstEmiDate() },
				{ "Last EMI Date", data.getLastEmiDate() },
				{ "Disbursal Status", data.getDisbursalStatus() },
				{ "Linked Loan Account No.", data.getLinkedLoanAccountNo() } };

		String[][] right = {
				{ "Installment Paid (Rs.)", data.getInstallmentPaid() },
				{ "Principal Paid (Rs.)", data.getPrincipalPaid() },
				{ "Interest Paid (Rs.)", data.getInterestPaid() },
				{ "Principal Outstanding (Rs.)", data.getPrincipalOutstanding() },
				{ "Overdue Principal Amount (Rs.)", data.getOverduePrincipalAmount() },
				{ "Future Principal Amount (Rs.)", data.getFuturePrincipalAmount() },
				{ "Overdue Interest Amount (Rs.)", data.getOverdueInterestAmount() },
				{ "Other Overdues (Rs.)", data.getOtherOverdues() },
				{ "Additional Interest Paid (Rs.)", data.getAdditionalInterestPaid() },
				{ "Additional Interest Due (Rs.)", data.getAdditionalInterestDue() },
				{ "Bounce Charges Paid (Rs.)", data.getBounceChargesPaid() },
				{ "Bounce Charges Due (Rs.)", data.getBounceChargesDue() },
				{ "Total Outstanding (Rs.)", data.getTotalOutstanding() },
				{ "Installment Overdue (Rs.)", data.getInstallmentOverdue() },
				{ "Unapplied Credits (Rs.)", data.getUnappliedCredits() },
				{ "Last Payment Amount (Rs.)", data.getLastPaymentAmount() } };

		int rows = Math.max(left.length, right.length);
		for (int i = 0; i < rows; i++) {
			if (i < left.length) {
				table.addCell(summaryCell(left[i][0], GREY, BOLD_FONT, Element.ALIGN_LEFT));
				table.addCell(summaryCell(left[i][1], GREY, NORMAL_FONT, Element.ALIGN_RIGHT));
			} else {
				table.addCell(summaryCell("", GREY, NORMAL_FONT, Element.ALIGN_LEFT));
				table.addCell(summaryCell("", GREY, NORMAL_FONT, Element.ALIGN_LEFT));
			}
			if (i < right.length) {
				table.addCell(summaryCell(right[i][0], LIGHT_GREEN, BOLD_FONT, Element.ALIGN_LEFT));
				table.addCell(summaryCell(right[i][1], LIGHT_GREEN, NORMAL_FONT, Element.ALIGN_RIGHT));
			} else {
				table.addCell(summaryCell("", LIGHT_GREEN, NORMAL_FONT, Element.ALIGN_LEFT));
				table.addCell(summaryCell("", LIGHT_GREEN, NORMAL_FONT, Element.ALIGN_LEFT));
			}
		}
		document.add(table);
	}

	private void addTransactionTable(Document document, AccountStatementPdfBean data) throws Exception {
		PdfPTable table = new PdfPTable(11);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 9, 9, 22, 8, 8, 6, 6, 6, 6, 7, 7 });

		// header row 1
		table.addCell(headerCell("Date", 2));
		table.addCell(headerCell("Value Date", 2));
		table.addCell(headerCell("Particulars", 2));
		table.addCell(headerCell("Debit", 2));
		table.addCell(headerCell("Credit", 2));
		table.addCell(groupHeaderCell("Additional Interest"));
		table.addCell(groupHeaderCell("Bounce Charges"));
		table.addCell(groupHeaderCell("Late Payment Charges"));

		// header row 2 (Due / Paid pairs)
		table.addCell(headerCell("Due", 1));
		table.addCell(headerCell("Paid", 1));
		table.addCell(headerCell("Due", 1));
		table.addCell(headerCell("Paid", 1));
		table.addCell(headerCell("Due", 1));
		table.addCell(headerCell("Paid", 1));

		for (TransactionRow row : data.getTransactions()) {
			table.addCell(bodyCell(row.getDate(), Element.ALIGN_LEFT));
			table.addCell(bodyCell(row.getValueDate(), Element.ALIGN_LEFT));
			table.addCell(bodyCell(row.getParticulars(), Element.ALIGN_LEFT));
			table.addCell(bodyCell(row.getDebit(), Element.ALIGN_RIGHT));
			table.addCell(bodyCell(row.getCredit(), Element.ALIGN_RIGHT));
			table.addCell(bodyCell(row.getAddlInterestDue(), Element.ALIGN_RIGHT));
			table.addCell(bodyCell(row.getAddlInterestPaid(), Element.ALIGN_RIGHT));
			table.addCell(bodyCell(row.getBounceChargesDue(), Element.ALIGN_RIGHT));
			table.addCell(bodyCell(row.getBounceChargesPaid(), Element.ALIGN_RIGHT));
			table.addCell(bodyCell(row.getLatePaymentDue(), Element.ALIGN_RIGHT));
			table.addCell(bodyCell(row.getLatePaymentPaid(), Element.ALIGN_RIGHT));
		}

		// totals
		PdfPCell totalLabel = new PdfPCell(new Phrase("All Total", TABLE_HEADER_FONT));
		totalLabel.setColspan(3);
		totalLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
		totalLabel.setPadding(3);
		table.addCell(totalLabel);
		table.addCell(bodyCell(sum(data.getTransactions(), 0), Element.ALIGN_RIGHT));
		table.addCell(bodyCell(sum(data.getTransactions(), 1), Element.ALIGN_RIGHT));
		table.addCell(bodyCell("0.00", Element.ALIGN_RIGHT));
		table.addCell(bodyCell("0.00", Element.ALIGN_RIGHT));
		table.addCell(bodyCell("0.00", Element.ALIGN_RIGHT));
		table.addCell(bodyCell("0.00", Element.ALIGN_RIGHT));
		table.addCell(bodyCell("0.00", Element.ALIGN_RIGHT));
		table.addCell(bodyCell("0.00", Element.ALIGN_RIGHT));

		document.add(table);
	}

	private void addNotesBlock(Document document, AccountStatementPdfBean data) throws Exception {
		document.add(new Paragraph(" "));
		Paragraph notif = new Paragraph("Important Notification:", BOLD_FONT);
		notif.setSpacingBefore(10);
		document.add(notif);

		document.add(new Paragraph(
				"\"We hereby declare that though our aggregate turnover in any preceding financial year from "
						+ "2017-18 onwards is more than the aggregate turnover notified under sub-rule (4) of rule 48, "
						+ "we are not required to prepare an invoice in terms of the provisions of the said sub-rule.\"",
				NORMAL_FONT));
		document.add(new Paragraph(
				"Note :  Insurance premium is including applicable tax and break-up of insurance premium and applicable "
						+ "tax will be available in the certificate of insurance provided by the insurance company.",
				NORMAL_FONT));
		document.add(new Paragraph(
				"Note :  This is computer generated statement and does not require signature.", NORMAL_FONT));

		document.add(new Paragraph(" "));
		document.add(new Paragraph("Description Of Service: Other financial services (997119)", NORMAL_FONT));
		document.add(new Paragraph("Service Tax Code : " + safe(data.getServiceTaxCode()), NORMAL_FONT));
		document.add(new Paragraph("GSTIN : " + safe(data.getFooterGstin()), NORMAL_FONT));
		document.add(new Paragraph("CIN : " + safe(data.getCin()), NORMAL_FONT));

		document.add(new Paragraph(" "));
		document.add(new Paragraph(
				"Corporate Office: 10th Floor, Office No. 101, 102 & 103, 2 North Avenue, Maker Maxity, Bandra Kurla "
						+ "Complex, Bandra (E), Mumbai - 400 051",
				NORMAL_FONT));
		document.add(new Paragraph(
				"Registered Office: Commerzone IT Park, Tower B, 1st Floor, No. 111, Mount Poonamallee Road, Porur, "
						+ "Chennai - 600116, Tamil Nadu.",
				NORMAL_FONT));
		document.add(new Paragraph("Contact Details: Toll-free: 18001036001 | Email: namaste@smfgindia.com",
				NORMAL_FONT));
	}

	private void addPageFooter(Document document, String text) throws Exception {
		Paragraph footer = new Paragraph(text, SMALL_FONT);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setSpacingBefore(20);
		document.add(footer);
	}

	/* ===================== Helper cell builders ===================== */

	private Paragraph labelValue(String label, String value) {
		Phrase phrase = new Phrase();
		phrase.add(new Chunk(label, BOLD_FONT));
		phrase.add(new Chunk(safe(value), NORMAL_FONT));
		return new Paragraph(phrase);
	}

	private PdfPCell summaryCell(String text, BaseColor bg, Font font, int align) {
		PdfPCell cell = new PdfPCell(new Phrase(safe(text), font));
		cell.setBackgroundColor(bg);
		cell.setHorizontalAlignment(align);
		cell.setPadding(3);
		cell.setBorderColor(BaseColor.WHITE);
		return cell;
	}

	/** Header cell that spans {@code rowspan} rows (single column). */
	private PdfPCell headerCell(String text, int rowspan) {
		PdfPCell cell = new PdfPCell(new Phrase(text, TABLE_HEADER_FONT));
		cell.setRowspan(rowspan);
		cell.setBackgroundColor(YELLOW);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(3);
		return cell;
	}

	/** Grouped header spanning two columns (e.g. Due/Paid pair). */
	private PdfPCell groupHeaderCell(String text) {
		PdfPCell cell = new PdfPCell(new Phrase(text, TABLE_HEADER_FONT));
		cell.setColspan(2);
		cell.setBackgroundColor(YELLOW);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(3);
		return cell;
	}

	private PdfPCell bodyCell(String text, int align) {
		PdfPCell cell = new PdfPCell(new Phrase(safe(text), SMALL_FONT));
		cell.setHorizontalAlignment(align);
		cell.setPadding(3);
		return cell;
	}

	private String sum(List<TransactionRow> rows, int col) {
		double total = 0;
		for (TransactionRow r : rows) {
			String v = col == 0 ? r.getDebit() : r.getCredit();
			total += parse(v);
		}
		return String.format("%,.2f", total);
	}

	private double parse(String value) {
		if (value == null || value.trim().isEmpty()) {
			return 0;
		}
		try {
			return Double.parseDouble(value.replace(",", ""));
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private String safe(String value) {
		return value == null ? "" : value;
	}

	/* ===================== Dummy data ===================== */

	/**
	 * Builds a fully populated bean with dummy values matching the sample
	 * statement. Replace this with data coming from the API.
	 */
	public AccountStatementPdfBean buildDummyData() {
		AccountStatementPdfBean data = new AccountStatementPdfBean();
		data.setCompanyName("SMFG India Credit Co. Ltd.");
		data.setStatementDate("22-MAY-2026");
		data.setPeriodFrom("14-MAY-2026");
		data.setPeriodTo("22-MAY-2026");
		data.setLoanAccountNumber("XXXXXXXX1234");
		data.setCustomerId("XXXXXXXX9876");

		data.setApplicantName("Jatin Kotani");
		data.setAddress("S/O: Ramesh Kotani, Sr. No. 27/2B, Sai Shraddha Niw, Nagar, Dhanori, "
				+ "Pune City, Pune, PUNE, MAHARASHTRA, PINCODE - 411015");
		data.setEmailId("jatinkotani11@gmail.com");
		data.setGstStateCode("27");
		data.setGstin("NA");
		data.setMobile("8668551937");
		data.setCkycId("XXXXXXXXXX4490");
		data.setBranchCode("2200");
		data.setBranchAddress("307 MEGH TOWERS II FLOOR, CHENNAI, TAMIL NADU, Pincode - 600095");

		data.setStatus("DISBURSED");
		data.setProductName("FLEXI CREDIT");
		data.setAmountFinance("350,000.00");
		data.setDisbursalDate("14-MAY-2026");
		data.setTenure("36");
		data.setRepaymentFrequency("Monthly");
		data.setRepaymentMode("ECS");
		data.setMonthsOnBook("0");
		data.setBalanceTenure("36");
		data.setInterestRate("Fixed");
		data.setRateOfInterest("20.00 %");
		data.setAmountDisbursedTillDate("337,610.00");
		data.setFirstEmiDate("05-JUN-2026");
		data.setLastEmiDate("05-MAY-2029");
		data.setDisbursalStatus("Fully Disbursed");
		data.setLinkedLoanAccountNo("1");

		data.setInstallmentPaid("0.00");
		data.setPrincipalPaid("0.00");
		data.setInterestPaid("0.00");
		data.setPrincipalOutstanding("350,000.00");
		data.setOverduePrincipalAmount("0.00");
		data.setFuturePrincipalAmount("350,000.00");
		data.setOverdueInterestAmount("0.00");
		data.setOtherOverdues("0.00");
		data.setAdditionalInterestPaid("0.00");
		data.setAdditionalInterestDue("0.00");
		data.setBounceChargesPaid("0.00");
		data.setBounceChargesDue("0.00");
		data.setTotalOutstanding("350,000.00");
		data.setInstallmentOverdue("0.00");
		data.setUnappliedCredits("0.00");
		data.setLastPaymentAmount("0.00");
		data.setLastPaymentDate("");
		data.setLatePaymentChargesPaid("0.00");
		data.setLatePaymentChargesDue("0.00");

		data.getTransactions().add(new TransactionRow("14-MAY-2026", "14-MAY-2026",
				"DUE - Processing Fee Pre-D", "10,500.00", "0.00", "0.00", "0.00", "0.00", "0.00", "0.00", "0.00"));
		data.getTransactions().add(new TransactionRow("14-MAY-2026", "14-MAY-2026",
				"DUE - IGST Processing Fee Pre-D", "1,890.00", "0.00", "0.00", "0.00", "0.00", "0.00", "0.00", "0.00"));
		data.getTransactions().add(new TransactionRow("14-MAY-2026", "14-MAY-2026",
				"Amt Financed-Payable", "0.00", "350,000.00", "0.00", "0.00", "0.00", "0.00", "0.00", "0.00"));
		data.getTransactions().add(new TransactionRow("14-MAY-2026", "14-MAY-2026",
				"PAID - Disbursal Amount", "337,610.00", "0.00", "0.00", "0.00", "0.00", "0.00", "0.00", "0.00"));

		data.setServiceTaxCode("AACD1707CST001");
		data.setFooterGstin("33AAACD1707C1ZI");
		data.setCin("U65191TN1994PLC079235");
		return data;
	}
}
