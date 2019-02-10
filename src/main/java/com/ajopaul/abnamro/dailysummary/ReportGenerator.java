package com.ajopaul.abnamro.dailysummary;

import com.ajopaul.abnamro.dailysummary.model.InputRecord;
import com.ajopaul.abnamro.dailysummary.model.ReportSummary;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Data
public class ReportGenerator {

    private final List<InputRecord> inputRecordList;
    private final String csvOutFilePath;

    public List<ReportSummary> getReportSummary() {

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

    public boolean exportToCSV() {
        boolean success = false;
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(csvOutFilePath))
        ) {
            StatefulBeanToCsv<ReportSummary> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(getReportSummary());
            success = true;
        } finally {
            return success;
        }
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
