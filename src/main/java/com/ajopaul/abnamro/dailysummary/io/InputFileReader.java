package com.ajopaul.abnamro.dailysummary.io;

import lombok.AllArgsConstructor;

import java.io.File;
import java.util.Scanner;

@AllArgsConstructor
public class InputFileReader {

    private final String[] programArguments;

    public String getInputFilePathFromProgramArgs(){
        String inputFilePath;
        if ( programArguments != null && programArguments.length > 0) {
            inputFilePath = programArguments[0].trim();
        } else {
            inputFilePath = readInputFilePathFromTerminal();
        }

        File inputFile = new File(inputFilePath);
        if (inputFile.exists() && !inputFile.isDirectory()) {
            return inputFilePath;
        } else {
            throw new RuntimeException("Invalid Input file path! Please check the file path.");
        }
    }

    public String readInputFilePathFromTerminal() {
        System.out.println("Please enter the complete path of `Input.txt`");

        System.out.print("\nEnter file path: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}