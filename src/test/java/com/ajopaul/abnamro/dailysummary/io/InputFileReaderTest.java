package com.ajopaul.abnamro.dailysummary.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputFileReaderTest {

    private InputFileReader inputFileReader;
    private String[] args;

    @BeforeEach
    public void setup() throws IOException {
        File inputFile = Files.createTempFile("sample-input-file", ".txt").toFile();
        args = new String[] {inputFile.getAbsolutePath()};
        inputFileReader = new InputFileReader(args);
    }

    @Test
    public void test_getInputFilePath() {


        String inputFilePath = inputFileReader.getInputFilePathFromProgramArgs();

        assertEquals(inputFilePath, args[0]);
    }

    @Test
    public void testInputFilePathFromTerminal() {
        String input = "/tmp/Input.txt";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("/tmp/Input.txt", inputFileReader.readInputFilePathFromTerminal());
    }
}
