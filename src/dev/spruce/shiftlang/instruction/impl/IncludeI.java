package dev.spruce.shiftlang.instruction.impl;

import dev.spruce.shiftlang.Interpreter;
import dev.spruce.shiftlang.Lexer;
import dev.spruce.shiftlang.instruction.Instruction;
import dev.spruce.shiftlang.instruction.InstructionData;
import dev.spruce.shiftlang.parsing.StringExpressionParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dev.spruce.shiftlang.Lexer.FILE_EXTENSION;

public class IncludeI extends Instruction {

    public IncludeI() {
        super("include");
    }

    @Override
    public void handleCommand(InstructionData data) {
        String scriptName = StringExpressionParser.parse(data.getCommandLogic());
        List<InstructionData> instructions;
        try {
            instructions = Lexer.parseInstructionsFromFile(Lexer.getMainScriptLocation() + File.separator + scriptName + FILE_EXTENSION);
        } catch (IOException e) {
            System.err.println("An error occurred while including script '" + scriptName + "'");
            throw new RuntimeException(e);
        }

        for (InstructionData instruction : instructions) {
            Interpreter.handleInstruction(instruction);
        }
    }
}
