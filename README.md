# Account Statement PDF Service

A standalone **Spring Boot 3.3 / Java 21** service that generates the SMFG India Credit
**Account Statement (SOA) PDF** using **iText 5**.

## Requirements
- JDK 21
- Maven 3.9+

## Run
```bash
mvn spring-boot:run
```

## Endpoints
| Method | URL | Description |
|--------|-----|-------------|
| GET | `/accountstatement/pdf` | Downloads the PDF using built-in **dummy data**. |
| POST | `/accountstatement/pdf` | Downloads the PDF using JSON data in the request body (`AccountStatementPdfBean`). |

Quick preview in a browser:
```
http://localhost:8080/accountstatement/pdf
```

Swagger UI:
```
http://localhost:8080/swagger-ui.html
```

## Project structure
```
src/main/java/com/bajaj/bfsd/accountstatement/
├── AccountStatementPdfApplication.java   # Spring Boot entry point
├── bean/AccountStatementPdfBean.java     # Data model (populated from API later)
├── service/AccountStatementPdfService.java # iText PDF generation logic
└── controller/AccountStatementPdfController.java # REST endpoints
```

## Wiring real API data
Replace the call to `buildDummyData()` with your API response mapped into
`AccountStatementPdfBean`, then call
`accountStatementPdfService.generateAccountStatementPdf(bean)`.
