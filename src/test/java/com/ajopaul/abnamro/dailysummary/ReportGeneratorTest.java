package com.ajopaul.abnamro.dailysummary;

import com.ajopaul.abnamro.dailysummary.model.InputRecord;
import com.ajopaul.abnamro.dailysummary.model.ReportSummary;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportGeneratorTest {

    private List<InputRecord> inputRecordList;
    private ReportGenerator reportGenerator;

    @BeforeEach
    public void setup() {
        inputRecordList = asList(InputRecord.builder()
                        .clientType("111")
                        .clientNumber("1111")
                        .accountNumber("1010")
                        .subAccountNumber("0101")
                        .exchangeCode("AAAA")
                        .productGroupCode("AA")
                        .symbol("AAAAAA")
                        .expirationDate("20000101")
                        .quantityLong(10.0)
                        .quantityShort(1.0)
                        .build(),
                InputRecord.builder()
                        .clientType("111")
                        .clientNumber("1111")
                        .accountNumber("1010")
                        .subAccountNumber("0101")
                        .exchangeCode("AAAB")
                        .productGroupCode("AB")
                        .symbol("AAAAAB")
                        .expirationDate("20000102")
                        .quantityLong(11.0)
                        .quantityShort(2.0)
                        .build(),
                InputRecord.builder()
                        .clientType("112")
                        .clientNumber("1112")
                        .accountNumber("1011")
                        .subAccountNumber("0102")
                        .exchangeCode("AAAA")
                        .productGroupCode("AA")
                        .symbol("AAAAAA")
                        .expirationDate("20000101")
                        .quantityLong(9.0)
                        .quantityShort(3.0)
                        .build(),
                InputRecord.builder()
                        .clientType("111")
                        .clientNumber("1111")
                        .accountNumber("1010")
                        .subAccountNumber("0101")
                        .exchangeCode("AAAA")
                        .productGroupCode("AA")
                        .symbol("AAAAAA")
                        .expirationDate("20000101")
                        .quantityLong(8.0)
                        .quantityShort(1.0)
                        .build(),
                InputRecord.builder()
                        .clientType("112")
                        .clientNumber("1112")
                        .accountNumber("1011")
                        .subAccountNumber("0102")
                        .exchangeCode("AAAB")
                        .productGroupCode("AB")
                        .symbol("AAAAAB")
                        .expirationDate("20000102")
                        .quantityLong(9.0)
                        .quantityShort(3.0)
                        .build(),
                InputRecord.builder()
                        .clientType("113")
                        .clientNumber("1113")
                        .accountNumber("1013")
                        .subAccountNumber("0103")
                        .exchangeCode("AAAA")
                        .productGroupCode("AA")
                        .symbol("AAAAAA")
                        .expirationDate("20000101")
                        .quantityLong(9.0)
                        .quantityShort(3.0)
                        .build(),
                InputRecord.builder()
                        .clientType("113")
                        .clientNumber("1113")
                        .accountNumber("1013")
                        .subAccountNumber("0103")
                        .exchangeCode("AAAA")
                        .productGroupCode("AA")
                        .symbol("AAAAAA")
                        .expirationDate("20000101")
                        .quantityLong(-6.0)
                        .quantityShort(3.0)
                        .build());

        reportGenerator = new ReportGenerator(inputRecordList, "/tmp/Output.csv");
    }

    @Test
    public void test_getReportSummary() {
        List<ReportSummary> reportSummaryList = reportGenerator.getReportSummary();

        assertThat(reportSummaryList).isEqualTo(asList(
                ReportSummary.builder()
                    .clientInformation("111111110100101")
                    .productInformation("AAAAAAAAAAAA20000101")
                    .netTotalTransactionAmount(16.0)
                    .build(),
                ReportSummary.builder()
                    .clientInformation("111111110100101")
                    .productInformation("AAABABAAAAAB20000102")
                    .netTotalTransactionAmount(9.0)
                    .build(),
                ReportSummary.builder()
                        .clientInformation("112111210110102")
                        .productInformation("AAAAAAAAAAAA20000101")
                        .netTotalTransactionAmount(6.0)
                        .build(),
                ReportSummary.builder()
                        .clientInformation("112111210110102")
                        .productInformation("AAABABAAAAAB20000102")
                        .netTotalTransactionAmount(6.0)
                        .build(),
                ReportSummary.builder()
                        .clientInformation("113111310130103")
                        .productInformation("AAAAAAAAAAAA20000101")
                        .netTotalTransactionAmount(-3.0)
                        .build()));
    }

    @Test
    public void test_exportToCSV() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        reportGenerator.exportToCSV();

        try (
                Reader reader = Files.newBufferedReader(Paths.get("/tmp/Output.csv"));
        ) {
            CSVReader csvReader = new CSVReader(reader);

            String[] nextRecord = csvReader.readNext();
            assertEquals("CLIENT_INFORMATION", nextRecord[0]);
            assertEquals("PRODUCT_INFORMATION", nextRecord[1]);
            assertEquals("TOTAL_TRANSACTION_AMOUNT", nextRecord[2]);

            List<ReportSummary> reportSummaryList = new ArrayList<>();
            while ((nextRecord = csvReader.readNext()) != null) {
                reportSummaryList.add(ReportSummary.builder()
                        .clientInformation(nextRecord[0])
                        .productInformation(nextRecord[1])
                        .netTotalTransactionAmount(Double.parseDouble(nextRecord[2]))
                        .build());
            }

            assertThat(reportSummaryList).isEqualTo(asList(
                    ReportSummary.builder()
                            .clientInformation("111111110100101")
                            .productInformation("AAAAAAAAAAAA20000101")
                            .netTotalTransactionAmount(16.0)
                            .build(),
                    ReportSummary.builder()
                            .clientInformation("111111110100101")
                            .productInformation("AAABABAAAAAB20000102")
                            .netTotalTransactionAmount(9.0)
                            .build(),
                    ReportSummary.builder()
                            .clientInformation("112111210110102")
                            .productInformation("AAAAAAAAAAAA20000101")
                            .netTotalTransactionAmount(6.0)
                            .build(),
                    ReportSummary.builder()
                            .clientInformation("112111210110102")
                            .productInformation("AAABABAAAAAB20000102")
                            .netTotalTransactionAmount(6.0)
                            .build(),
                    ReportSummary.builder()
                            .clientInformation("113111310130103")
                            .productInformation("AAAAAAAAAAAA20000101")
                            .netTotalTransactionAmount(-3.0)
                            .build()));
        }
    }
 }
