package com.bajaj.bfsd.accountstatement.service;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bajaj.bfsd.accountstatement.bean.AccountStatementPdfBean;
import com.bajaj.bfsd.accountstatement.bean.AccountStatementPdfBean.TransactionRow;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Generates the Account Statement (SOA) PDF using the <b>FreeMarker + HTML</b>
 * approach:
 * <ol>
 * <li>FreeMarker renders the {@code account-statement.ftlh} template into an
 * XHTML string using the supplied data.</li>
 * <li>openhtmltopdf converts that XHTML into a PDF.</li>
 * </ol>
 * This is an alternative to the programmatic iText approach in
 * {@link AccountStatementPdfService} and is usually easier to style/maintain.
 */
@Service
public class AccountStatementHtmlPdfService {

	private static final String TEMPLATE_NAME = "account-statement.html";

	private final Configuration freemarkerConfig;
	private final AccountStatementPdfService dummyDataSource;

	@Autowired
	public AccountStatementHtmlPdfService(Configuration freemarkerConfig,
			AccountStatementPdfService dummyDataSource) {
		this.freemarkerConfig = freemarkerConfig;
		this.dummyDataSource = dummyDataSource;
	}

	/**
	 * Renders the statement to PDF via FreeMarker + openhtmltopdf.
	 *
	 * @param data statement data; if {@code null} dummy data is used.
	 * @return PDF content as bytes.
	 */
	public byte[] generatePdf(AccountStatementPdfBean data) {
		if (data == null) {
			data = dummyDataSource.buildDummyData();
		}
		try {
			String html = renderHtml(data);
			return htmlToPdf(html);
		} catch (Exception e) {
			throw new IllegalStateException("Error generating Account Statement PDF (FreeMarker)", e);
		}
	}

	/** Renders the FreeMarker template into an XHTML string. */
	public String renderHtml(AccountStatementPdfBean data) throws Exception {
		if (data == null) {
			data = dummyDataSource.buildDummyData();
		}
		Map<String, Object> model = new HashMap<>();
		model.put("data", data);
		model.put("totalDebit", sum(data.getTransactions(), true));
		model.put("totalCredit", sum(data.getTransactions(), false));

		Template template = freemarkerConfig.getTemplate(TEMPLATE_NAME);
		StringWriter writer = new StringWriter();
		template.process(model, writer);
		return writer.toString();
	}

	/** Converts an XHTML string into PDF bytes using openhtmltopdf. */
	private byte[] htmlToPdf(String html) throws Exception {
		try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
			PdfRendererBuilder builder = new PdfRendererBuilder();
			builder.useFastMode();
			builder.withHtmlContent(html, null);
			builder.toStream(os);
			builder.run();
			return os.toByteArray();
		}
	}

	private String sum(List<TransactionRow> rows, boolean debit) {
		double total = 0;
		for (TransactionRow r : rows) {
			total += parse(debit ? r.getDebit() : r.getCredit());
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
}
