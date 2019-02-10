package com.ajopaul.abnamro.dailysummary;

import com.ajopaul.abnamro.dailysummary.model.InputRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class InputRecordParserTest {

    InputRecordParser inputRecordParser;
    @BeforeEach
    public void setup() {
        inputRecordParser = new InputRecordParser();
    }

    @Test
    public void testParse_InputRecordList() throws IOException {
        Path path = Files.createTempFile("sample-input-file", ".txt");
        File file = path.toFile();
        String inputs = "XXX1111111110100101XXXXXXAAAAAAAAAAAA20000101XXXXXXX000000000110000000009XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                "\n" +
                "XXX1112111210110102XXXXXXABAAABAAAAAB20000102XXXXXXX000000000120000000008XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                "\n";
        Files.write(path, inputs.getBytes());
        file.deleteOnExit();
        String inputFilePath =  file.getAbsolutePath();
        List<InputRecord> inputRecordList = inputRecordParser.parseInputFile(inputFilePath);

        assertThat(inputRecordList).isEqualTo(asList(InputRecord.builder()
                        .clientType("1111")
                        .clientNumber("1111")
                        .accountNumber("1010")
                        .subAccountNumber("0101")
                        .exchangeCode("AAAA")
                        .productGroupCode("AA")
                        .symbol("AAAAAA")
                        .expirationDate("20000101")
                        .quantityLong(11.0)
                        .quantityShort(9.0)
                        .build(),
                InputRecord.builder()
                        .clientType("1112")
                        .clientNumber("1112")
                        .accountNumber("1011")
                        .subAccountNumber("0102")
                        .exchangeCode("AAAB")
                        .productGroupCode("AB")
                        .symbol("AAAAAB")
                        .expirationDate("20000102")
                        .quantityLong(12.0)
                        .quantityShort(8.0)
                        .build()));

    }

    @Test
    public void testParse_InputRecordList_whenNumericValuesAreInvalid() throws IOException {
        Path path = Files.createTempFile("sample-input-file", ".txt");
        File file = path.toFile();
        String inputs = "XXX1111111110100101XXXXXXAAAAAAAAAAAA20000101XXXXXXX000000000XX0000000009XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                "\n" +
                "XXX1112111210110102XXXXXXABAAABAAAAAB20000102XXXXXXX00000000012000000000XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                "\n";
        Files.write(path, inputs.getBytes());
        file.deleteOnExit();
        String inputFilePath = file.getAbsolutePath();

        List<InputRecord> inputRecordList = inputRecordParser.parseInputFile(inputFilePath);

        assertThat(inputRecordList).isEqualTo(asList(InputRecord.builder()
                        .clientType("1111")
                        .clientNumber("1111")
                        .accountNumber("1010")
                        .subAccountNumber("0101")
                        .exchangeCode("AAAA")
                        .productGroupCode("AA")
                        .symbol("AAAAAA")
                        .expirationDate("20000101")
                        .quantityLong(0.0)
                        .quantityShort(9.0)
                        .build(),
                InputRecord.builder()
                        .clientType("1112")
                        .clientNumber("1112")
                        .accountNumber("1011")
                        .subAccountNumber("0102")
                        .exchangeCode("AAAB")
                        .productGroupCode("AB")
                        .symbol("AAAAAB")
                        .expirationDate("20000102")
                        .quantityLong(12.0)
                        .quantityShort(0.0)
                        .build()));

    }

    @Test
    public void testParse_InputRecordList_whenSomeValuesAreInvalid() throws IOException {
        Path path = Files.createTempFile("sample-input-file", ".txt");
        File file = path.toFile();
        String inputs = "xxxxxx" +
                "\n";
        Files.write(path, inputs.getBytes());
        file.deleteOnExit();
        String inputFilePath =  file.getAbsolutePath();

        List<InputRecord> inputRecordList = inputRecordParser.parseInputFile(inputFilePath);

        assertThat(inputRecordList).isEqualTo(singletonList(InputRecord.builder()
                .clientType("")
                .clientNumber("")
                .accountNumber("")
                .subAccountNumber("")
                .exchangeCode("")
                .productGroupCode("")
                .symbol("")
                .expirationDate("")
                .quantityLong(0.0)
                .quantityShort(0.0)
                .build()));

    }
}