package com.ajopaul.abnamro.dailysummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReportGenerator {
    public static List<ReportSummary> report(List<InputRecord> inputRecordList) {

        List<ReportSummary> reportSummaryList = new ArrayList<>();
        inputRecordList.forEach(
                inputRecord -> {
                    Optional<ReportSummary> reportSummary = reportSummaryList
                            .stream()
                            .filter(r -> r.getClientInformation().equals(extractClientInformation(inputRecord))
                                    && r.getProductInformation().equals(extractProductInformation(inputRecord)))
                            .findAny();

                    if (reportSummary.isPresent()) {
                        reportSummary.get().setNetTotalTransactionAmount(
                                reportSummary.get().getNetTotalTransactionAmount() + extractTotalTransaction(inputRecord));
                    } else {
                        reportSummaryList.add(ReportSummary.builder()
                                .clientInformation(extractClientInformation(inputRecord))
                                .productInformation(extractProductInformation(inputRecord))
                                .netTotalTransactionAmount(extractTotalTransaction(inputRecord))
                                .build());
                    }
                });

        return reportSummaryList;
    }

    private static String extractClientInformation(InputRecord record) {
        return record.getClientType() +
                record.getClientNumber() +
                record.getAccountNumber() +
                record.getSubAccountNumber();
    }

    private static String extractProductInformation(InputRecord record) {
        return record.getExchangeCode() +
                record.getProductGroupCode() +
                record.getSymbol() +
                record.getExpirationDate();
    }

    private static Double extractTotalTransaction(InputRecord inputRecord) {
        return inputRecord.getQuantityLong() - inputRecord.getQuantityShort();
    }
}
