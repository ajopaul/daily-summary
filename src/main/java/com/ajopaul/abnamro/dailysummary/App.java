package com.ajopaul.abnamro.dailysummary;

import com.ajopaul.abnamro.dailysummary.io.InputFileReader;
import com.ajopaul.abnamro.dailysummary.model.InputRecord;

import java.io.IOException;
import java.util.List;

/**
 * Daily Summary Report App
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        InputFileReader inputFileReader = new InputFileReader(args);
        String inputFilePath = inputFileReader.getInputFilePathFromProgramArgs();

        InputRecordParser inputRecordParser = new InputRecordParser();

        List<InputRecord> inputRecordList = inputRecordParser.parseInputFile(inputFilePath);

        ReportGenerator reportGenerator = new ReportGenerator(inputRecordList, "./Output.csv");
        boolean success = reportGenerator.exportToCSV();
        if (success) {
            System.out.println("\n***********************************");
            System.out.println("CSV Generated at path: "+reportGenerator.getCsvOutFilePath());
            System.out.println("***********************************\n");
        } else {
            System.out.println("\nCSV Generation failed!\n");
        }
    }
}
