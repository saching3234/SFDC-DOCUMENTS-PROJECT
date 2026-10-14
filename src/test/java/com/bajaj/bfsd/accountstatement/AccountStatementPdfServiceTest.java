package com.bajaj.bfsd.accountstatement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.bajaj.bfsd.accountstatement.service.AccountStatementPdfService;

class AccountStatementPdfServiceTest {

	private final AccountStatementPdfService service = new AccountStatementPdfService();

	@Test
	void generatesPdfWithDummyData() {
		byte[] pdf = service.generateAccountStatementPdf(null);
		assertNotNull(pdf);
		assertTrue(pdf.length > 0, "PDF should not be empty");
		// PDF files start with the "%PDF" magic bytes
		assertTrue(pdf[0] == '%' && pdf[1] == 'P' && pdf[2] == 'D' && pdf[3] == 'F',
				"Output should be a valid PDF");
	}
}
