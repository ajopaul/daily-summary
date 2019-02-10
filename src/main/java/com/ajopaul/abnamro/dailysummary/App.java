package com.ajopaul.abnamro.dailysummary;

import com.ajopaul.abnamro.dailysummary.io.InputFileReader;
import com.ajopaul.abnamro.dailysummary.model.InputRecord;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.util.List;

/**
 * Daily Summary Report App
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        InputFileReader inputFileReader = new InputFileReader(args);
        String inputFilePath = inputFileReader.getInputFilePathFromProgramArgs();

        InputRecordParser inputRecordParser = new InputRecordParser();

        List<InputRecord> inputRecordList = inputRecordParser.parseInputFile(inputFilePath);
        ReportGenerator reportGenerator = new ReportGenerator(inputRecordList, "./Output.csv");
        reportGenerator.exportToCSV();
    }
}
