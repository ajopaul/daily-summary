package com.ajopaul.abnamro.dailysummary;

import com.ajopaul.abnamro.dailysummary.model.InputRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class InputRecordParser {

    public static final String EMPTY = "";
    public static final double ZERO = 0.0;

    public List<InputRecord> parseInputFile(String inputFilePath) throws IOException {
        return Files.readAllLines(Paths.get(inputFilePath))
                .stream()
                .map(InputRecordParser::buildInputRecord)
                .collect(Collectors.toList());

    }

    private static InputRecord buildInputRecord(String line) {
        return InputRecord.builder()
                .clientType(safeExtractValue(line, 3, 7))
                .clientNumber(safeExtractValue(line, 7, 11))
                .accountNumber(safeExtractValue(line, 11, 15))
                .subAccountNumber(safeExtractValue(line, 15, 19))
                .exchangeCode(safeExtractValue(line, 27, 31))
                .productGroupCode(safeExtractValue(line, 25, 27))
                .symbol(safeExtractValue(line, 31, 37))
                .expirationDate(safeExtractValue(line, 37, 45))
                .quantityLong(safeParseDouble(safeExtractValue(line, 52, 63)))
                .quantityShort(safeParseDouble(safeExtractValue(line, 63, 73)))
                .build();
    }

    private static String safeExtractValue(String line, int beginIndex, int endIndex) {
        try {
            return line.substring(beginIndex, endIndex).trim();
        } catch (StringIndexOutOfBoundsException s) {
            return EMPTY;
        }
    }

    private static double safeParseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException | NullPointerException nfe) {
            return ZERO;
        }
    }
}
