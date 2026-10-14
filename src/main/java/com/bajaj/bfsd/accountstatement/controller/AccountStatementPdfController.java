package com.bajaj.bfsd.accountstatement.controller;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bajaj.bfsd.accountstatement.bean.AccountStatementPdfBean;
import com.bajaj.bfsd.accountstatement.service.AccountStatementHtmlPdfService;
import com.bajaj.bfsd.accountstatement.service.AccountStatementPdfService;
import com.bajaj.bfsd.accountstatement.soabeans.SoaMapper;
import com.bajaj.bfsd.accountstatement.soabeans.SoaResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller that generates the Account Statement (SOA) PDF.
 *
 * <p>Two generation strategies are exposed:</p>
 * <ul>
 * <li><b>iText</b> (programmatic) - {@code /pdf}, {@code /pdf/download}</li>
 * <li><b>FreeMarker + openhtmltopdf</b> (HTML template) - {@code /html},
 * {@code /pdf/freemarker}</li>
 * </ul>
 */
@RestController
@RequestMapping("/accountstatement")
@Tag(name = "Account Statement PDF", description = "Generate SOA PDF with iText or FreeMarker")
public class AccountStatementPdfController {

	private final AccountStatementPdfService accountStatementPdfService;
	private final AccountStatementHtmlPdfService accountStatementHtmlPdfService;
	private final SoaMapper soaMapper;

	@Autowired
	public AccountStatementPdfController(AccountStatementPdfService accountStatementPdfService,
			AccountStatementHtmlPdfService accountStatementHtmlPdfService,
			SoaMapper soaMapper) {
		this.accountStatementPdfService = accountStatementPdfService;
		this.accountStatementHtmlPdfService = accountStatementHtmlPdfService;
		this.soaMapper = soaMapper;
	}

	/* ===================== iText approach ===================== */

	/**
	 * Generates the statement PDF using dummy data. Handy for a quick preview /
	 * download in the browser.
	 */
	@Operation(summary = "Download Account Statement PDF (iText, dummy data)")
	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	@CrossOrigin
	public ResponseEntity<byte[]> downloadDummyPdf() {
		byte[] pdf = accountStatementPdfService.generateAccountStatementPdf(null);
		return buildPdfResponse(pdf);
	}

	/**
	 * Generates the statement PDF from the data supplied in the request body.
	 * Once the source API is integrated, map its response into
	 * {@link AccountStatementPdfBean} and post it here.
	 */
	@Operation(summary = "Download Account Statement PDF (iText, from data)")
	@PostMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	@CrossOrigin
	public ResponseEntity<byte[]> downloadPdf(@RequestBody(required = false) AccountStatementPdfBean request) {
		byte[] pdf = accountStatementPdfService.generateAccountStatementPdf(request);
		return buildPdfResponse(pdf);
	}

	/**
	 * Streams the statement PDF directly to the HTTP response output stream so the
	 * browser downloads it immediately when the endpoint is hit.
	 */
	@Operation(summary = "Stream & directly download Account Statement PDF (iText)")
	@GetMapping(value = "/pdf/download", produces = MediaType.APPLICATION_PDF_VALUE)
	@CrossOrigin
	public void streamPdf(HttpServletResponse response) throws IOException {
		byte[] pdf = accountStatementPdfService.generateAccountStatementPdf(null);
		writePdf(response, pdf, "AccountStatement.pdf");
	}

	/* ===================== FreeMarker (HTML) approach ===================== */

	/**
	 * Previews the rendered FreeMarker HTML in the browser (before it is turned
	 * into a PDF). Handy for tweaking the template layout.
	 */
	@Operation(summary = "Preview Account Statement HTML (FreeMarker)")
	@GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
	@CrossOrigin
	public ResponseEntity<String> previewHtml() throws Exception {
		String html = accountStatementHtmlPdfService.renderHtml(null);
		return ResponseEntity.ok(html);
	}

	/**
	 * Generates the statement PDF using the FreeMarker template + openhtmltopdf
	 * (dummy data) and streams it as a direct download.
	 */
	@Operation(summary = "Download Account Statement PDF (FreeMarker, dummy data)")
	@GetMapping(value = "/pdf/freemarker", produces = MediaType.APPLICATION_PDF_VALUE)
	@CrossOrigin(exposedHeaders = HttpHeaders.CONTENT_DISPOSITION)
	public void downloadPdfFreemarker(HttpServletResponse response) throws IOException {
		byte[] pdf = accountStatementHtmlPdfService.generatePdf(null);
		writePdf(response, pdf, "AccountStatement.pdf");
	}

	/**
	 * Generates the statement PDF from POSTed JSON data using the FreeMarker
	 * template. This is the endpoint to use once real API data is available.
	 */
	@Operation(summary = "Download Account Statement PDF (FreeMarker, from data)")
	@PostMapping(value = "/pdf/freemarker", produces = MediaType.APPLICATION_PDF_VALUE)
	@CrossOrigin(exposedHeaders = HttpHeaders.CONTENT_DISPOSITION)
	public void downloadPdfFreemarkerFromData(
			@RequestBody(required = false) AccountStatementPdfBean request,
			HttpServletResponse response) throws IOException {
		byte[] pdf = accountStatementHtmlPdfService.generatePdf(request);
		writePdf(response, pdf, "AccountStatement.pdf");
	}

	/* ===================== SOA (real API JSON) ===================== */

	/**
	 * Accepts the raw SOA API JSON, maps it to the template bean and returns the
	 * generated PDF (FreeMarker) as a direct download.
	 */
	@Operation(summary = "Download Account Statement PDF from SOA API JSON (FreeMarker)")
	@PostMapping(value = "/soa/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	@CrossOrigin(exposedHeaders = HttpHeaders.CONTENT_DISPOSITION)
	public void soaPdf(@RequestHeader HttpHeaders headers ,@RequestBody SoaResponse soaResponse, HttpServletResponse response) throws IOException {
		AccountStatementPdfBean bean = soaMapper.toPdfBean(soaResponse);
		byte[] pdf = accountStatementHtmlPdfService.generatePdf(bean);
		writePdf(response, pdf, "AccountStatement.pdf");
	}

	/**
	 * Accepts the raw SOA API JSON and returns the rendered HTML (handy for
	 * verifying the mapping in a browser before producing the PDF).
	 */
	@Operation(summary = "Preview Account Statement HTML from SOA API JSON")
	@PostMapping(value = "/soa/html", produces = MediaType.TEXT_HTML_VALUE)
	@CrossOrigin
	public ResponseEntity<String> soaHtml(@RequestBody SoaResponse soaResponse) throws Exception {
		AccountStatementPdfBean bean = soaMapper.toPdfBean(soaResponse);
		return ResponseEntity.ok(accountStatementHtmlPdfService.renderHtml(bean));
	}

	/* ===================== Helpers ===================== */

	/** Writes the PDF bytes to the response as a direct download. */
	private void writePdf(HttpServletResponse response, byte[] pdf, String fileName) throws IOException {
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		response.setContentLength(pdf.length);
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
		// Expose the header so browser JS can read the filename (CORS)
		response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		try (OutputStream out = response.getOutputStream()) {
			out.write(pdf);
			out.flush();
		}
	}

	private ResponseEntity<byte[]> buildPdfResponse(byte[] pdf) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "AccountStatement.pdf");
		headers.setContentLength(pdf.length);
		return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
	}
}
