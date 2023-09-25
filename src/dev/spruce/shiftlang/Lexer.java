package dev.spruce.shiftlang;

import dev.spruce.shiftlang.instruction.Instruction;
import dev.spruce.shiftlang.instruction.InstructionData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public static List<InstructionData> parseInstructionsFromFile(String fileName) throws IOException {
        List<InstructionData> instructions = new ArrayList<>();

        File readFile = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        StringBuilder finalString = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            finalString.append(line);
        }
        reader.close();

        String[] instructionData = finalString.toString().split(";");

        for (String s : instructionData) {
            instructions.add(parseInstruction(s));
        }

        return instructions;
    }

    public static InstructionData parseInstruction(String instructionString) {
        String[] instructionData = instructionString.split(" ");
        String commandWord = instructionData[0];
        int offset = commandWord.length() + 1;
        return new InstructionData(commandWord, instructionString.substring(offset), instructionString);
    }
}