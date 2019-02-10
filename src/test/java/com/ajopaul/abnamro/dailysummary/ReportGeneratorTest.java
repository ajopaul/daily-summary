package com.ajopaul.abnamro.dailysummary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportGeneratorTest {

    private List<InputRecord> inputRecordList;

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
    }

    @Test
    public void testReportSummary() {
        List<ReportSummary> reportSummaryList = ReportGenerator.report(inputRecordList);

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
