package com.ajopaul.abnamro.dailysummary;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println(args[0]);
        List<InputRecord> inputRecordList = SystemFileReader.parse(args[0]);
        List<ReportSummary> reportSummaryList = ReportGenerator.report(inputRecordList);
        System.out.println(reportSummaryList.size());
        System.out.println(reportSummaryList);
    }
}
